package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Controller.researcherController;
import Model.Admin;
import Model.Researcher;



@Path("/researchers")
public class researcherService {
	
	researcherController researchers = new researcherController();

	
	// get all types
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return researchers.viewResearchers();
	}


	// add types
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Researcher rsc = new Researcher();
		
		
		 rsc.setPassword(djosnObj.get("password").getAsString());
		 rsc.setAddress(djosnObj.get("address").getAsString());
		 rsc.setEmail(djosnObj.get("email").getAsString());
		 rsc.setPhone(djosnObj.get("phone").getAsString());
	   
		// Read the values from the JSON object
	
		String output = researchers.addResearchers(rsc);
		return output;

	}
	
	
	//login
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterbuyerlogin(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Researcher rsc = new Researcher();
		
		
		rsc.setEmail(djosnObj.get("email").getAsString());
		rsc.setPassword(djosnObj.get("password").getAsString());
	   
		// Read the values from the JSON object
	
		String output = researchers.loginResearchers(rsc);
		return output;

	}
	

	
	// update Types
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppType(String TypeData) {

			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			Researcher rsc = new Researcher();

			rsc.setUid(djosnObj.get("userId").getAsInt());
			rsc.setPassword(djosnObj.get("password").getAsString());
			rsc.setAddress(djosnObj.get("address").getAsString());
			rsc.setEmail(djosnObj.get("email").getAsString());
			rsc.setPhone(djosnObj.get("phone").getAsString());
		
			
			String output = researchers.updateResearchers(rsc);
			return output;
		}
	
	
	
		// delete Types
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletetype(String TypeData) {
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();

			Researcher rsc = new Researcher();
			
			// Read the value from the element <ID>
	    	rsc.setUid(doc.get("userId").getAsInt());
			
			String output = researchers.deleteResearchers(rsc);
			return output;
		}
	

}

