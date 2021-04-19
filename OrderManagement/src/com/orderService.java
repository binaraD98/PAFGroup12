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

import Controller.orderController;

import Model.Order;

@Path("/orders")
public class orderService {
	
	orderController orders = new orderController();

	
	// get all orders
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return orders.viewOrders();
	}


	// add orders
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		Order odr = new Order();
		
		odr.setPid(djosnObj.get("productId").getAsInt());
		odr.setUid(djosnObj.get("userId").getAsInt());
		odr.setQty(djosnObj.get("quantity").getAsInt());
		odr.setName(djosnObj.get("name").getAsString());
		odr.setAddress(djosnObj.get("address").getAsString());
		odr.setEmail(djosnObj.get("email").getAsString());
		odr.setPhone(djosnObj.get("phone").getAsString());
		odr.setTotal(djosnObj.get("total").getAsDouble());
		// Read the values from the JSON object
	
		String output = orders.addOrders(odr);
		return output;

	}

	
	
	
	// update orders
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppType(String TypeData) {

			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			Order odr = new Order();

			odr.setOid(djosnObj.get("orderId").getAsInt());
			odr.setPid(djosnObj.get("productId").getAsInt());
			odr.setUid(djosnObj.get("userId").getAsInt());
			odr.setQty(djosnObj.get("quantity").getAsInt());
			odr.setName(djosnObj.get("name").getAsString());
			odr.setAddress(djosnObj.get("address").getAsString());
			odr.setEmail(djosnObj.get("email").getAsString());
			odr.setPhone(djosnObj.get("phone").getAsString());
			odr.setTotal(djosnObj.get("total").getAsDouble());
			
			String output = orders.updateOrders(odr);
			return output;
		}
	
	
	


		// delete orders
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletetype(String TypeData) {
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();

//			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			Order odr = new Order();
			
			// Read the value from the element <ID>
	    	odr.setOid(doc.get("orderId").getAsInt());
			//String id = doc.get("appointment_Id").getAsString();
			String output = orders.deleteOrders(odr);
			return output;
		}
	
}
