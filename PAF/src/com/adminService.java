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

import Controller.adminController;
import Model.Admin;
import Model.Buyer;
import Model.Researcher;




@Path("/admins")
public class adminService {
	
	adminController admins = new adminController();

	
	// get all types
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return admins.viewAdmins();
	}


	// add types
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Admin adm = new Admin();
		
		
		adm.setName(djosnObj.get("name").getAsString());
		adm.setCompany(djosnObj.get("company").getAsString());
		adm.setAddress(djosnObj.get("address").getAsString());
		adm.setEmail(djosnObj.get("email").getAsString());
		adm.setPhone(djosnObj.get("phone").getAsString());
		adm.setPassword(djosnObj.get("password").getAsString());
		adm.setAmount(djosnObj.get("amount").getAsString());
		adm.setlisencenum(djosnObj.get("lisencenum").getAsString());
	   
		// Read the values from the JSON object
	
		String output = admins.addAdmins(adm);
		return output;

	}

	
	/////login/////
	
	
	

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterbuyerlogin(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Admin adm = new Admin();
		
		
		adm.setEmail(djosnObj.get("email").getAsString());
		adm.setPassword(djosnObj.get("password").getAsString());
	   
		// Read the values from the JSON object
	
		String output = admins.loginAdmins(adm);
		return output;

	}
	
	
	
//	
//	// update Types
//		@PUT
//		@Path("/")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.TEXT_PLAIN)
//		public String updateAppType(String TypeData) {
//
//			// Convert the input string to a JSON object
//			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
//			Admin adm = new Admin();
//
//			adm.setAid(djosnObj.get("adminId").getAsInt());
//		    adm.setName(djosnObj.get("name").getAsString());
//			adm.setCompany(djosnObj.get("company").getAsString());
//			adm.setAddress(djosnObj.get("address").getAsString());
//			adm.setEmail(djosnObj.get("email").getAsString());
//			adm.setPhone(djosnObj.get("phone").getAsString());
//			adm.setPassword(djosnObj.get("password").getAsString());
//			adm.setAmount(djosnObj.get("amount").getAsString());
//			adm.setlisencenum(djosnObj.get("lisencenum").getAsString());
//			
//			String output = admins.updateAdmins(adm);
//			return output;
//		}
//	
	
		
//		
//		// update Types
//			@PUT
//			@Path("/")
//			@Consumes(MediaType.APPLICATION_JSON)
//			@Produces(MediaType.TEXT_PLAIN)
//			public String updateAppType(String TypeData) {
//
//				// Convert the input string to a JSON object
//				JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
//				Admin adm = new Admin();
//
//				adm.setAid(djosnObj.get("adminId").getAsInt());
//				adm.setName(djosnObj.get("name").getAsString());
//				adm.setCompany(djosnObj.get("company").getAsString());
//				adm.setAddress(djosnObj.get("address").getAsString());
//				adm.setEmail(djosnObj.get("email").getAsString());
//				adm.setPhone(djosnObj.get("phone").getAsString());
//				adm.setPassword(djosnObj.get("password").getAsString());
//				adm.setAmount(djosnObj.get("amount").getAsString());
//				adm.setlisencenum(djosnObj.get("lisencenum").getAsString());
//			
//				
//				String output = admins.updateAdmins(adm);
//				return output;
//			}
//		
//		
//		
//	
//
//
//		// delete Types
//		
//		@DELETE
//		@Path("/")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.TEXT_PLAIN)
//		public String deletetype(String TypeData) {
//			// Convert the input string to a JSON object
//			JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();
//
////			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
//			Admin adm = new Admin();
//			
//			// Read the value from the element <ID>
//	    	adm.setAid(doc.get("adminId").getAsInt());
//			//String id = doc.get("appointment_Id").getAsString();
//			String output = admins.deleteAdmins(adm);
//			return output;
//		}
//	
		
		
		// update Types
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateAppType(String TypeData) {

				// Convert the input string to a JSON object
				JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
				Admin adm = new Admin();

				adm.setUid(djosnObj.get("userId").getAsInt());
				adm.setName(djosnObj.get("name").getAsString());
				adm.setCompany(djosnObj.get("company").getAsString());
				adm.setAddress(djosnObj.get("address").getAsString());
				adm.setEmail(djosnObj.get("email").getAsString());
				adm.setPhone(djosnObj.get("phone").getAsString());
				adm.setPassword(djosnObj.get("password").getAsString());
				adm.setAmount(djosnObj.get("amount").getAsString());
				adm.setlisencenum(djosnObj.get("lisencenum").getAsString());
			
				
				String output = admins.updateAdmins(adm);
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

//				JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
				Admin adm = new Admin();
				
				// Read the value from the element <ID>
		    	adm.setUid(doc.get("userId").getAsInt());
				//String id = doc.get("appointment_Id").getAsString();
				String output = admins.deleteAdmins(adm);
				return output;
			}

}