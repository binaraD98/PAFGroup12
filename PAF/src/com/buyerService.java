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

import Controller.buyerController;
import Model.Buyer;


@Path("/buyers")
public class buyerService  {
	
	buyerController buyers = new buyerController();

	
	// get all types
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return buyers.viewBuyers();
	}


	// add types
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Buyer byr = new Buyer();
		
		
		byr.setName(djosnObj.get("name").getAsString());
		byr.setCompany(djosnObj.get("company").getAsString());
		byr.setAddress(djosnObj.get("address").getAsString());
		byr.setEmail(djosnObj.get("email").getAsString());
		byr.setPhone(djosnObj.get("phone").getAsString());
		byr.setPassword(djosnObj.get("password").getAsString());
	   
		// Read the values from the JSON object
	
		String output = buyers.addBuyers(byr);
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
	
		Buyer byr = new Buyer();
		
		
		byr.setEmail(djosnObj.get("email").getAsString());
		byr.setPassword(djosnObj.get("password").getAsString());
	   
		// Read the values from the JSON object
	
		String output = buyers.loginBuyers(byr);
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
			
			Buyer byr = new Buyer();

			byr.setUid(djosnObj.get("buyerId").getAsInt());
			byr.setName(djosnObj.get("name").getAsString());
			byr.setCompany(djosnObj.get("company").getAsString());
			byr.setAddress(djosnObj.get("address").getAsString());
			byr.setEmail(djosnObj.get("email").getAsString());
			byr.setPhone(djosnObj.get("phone").getAsString());
			byr.setPassword(djosnObj.get("password").getAsString());
		
			
			String output = buyers.updateBuyers(byr);
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

			Buyer byr = new Buyer();
			
			// Read the value from the element <ID>
	    	byr.setUid(doc.get("buyerId").getAsInt());
			
			String output = buyers.deleteBuyers(byr);
			return output;
		}
	

}

