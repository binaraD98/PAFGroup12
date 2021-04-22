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

import Controller.cartController;
import Model.Order;
import Model.shoppingCart;


@Path("/cart")
public class cartService {
cartController crt = new cartController();

	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllFromCart() {
		return crt.viewCart();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		shoppingCart scart = new shoppingCart();
		
		scart.setPid(djosnObj.get("productId").getAsInt());
		scart.setUid(djosnObj.get("userId").getAsInt());
		scart.setQty(djosnObj.get("quantity").getAsInt());
		
		
		// Read the values from the JSON object
	
		String output = crt.addToCart(scart);
		return output;

	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCart(String TypeData) {

		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
		shoppingCart scart = new shoppingCart();

		scart.setCid(djosnObj.get("cartId").getAsInt());
		scart.setPid(djosnObj.get("productId").getAsInt());
		scart.setUid(djosnObj.get("userId").getAsInt());
		scart.setQty(djosnObj.get("quantity").getAsInt());
		
		
		String output = crt.updateCart(scart);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFromCart(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();


		shoppingCart scart = new shoppingCart();
		
		// Read the value from the element <ID>
    	scart.setCid(doc.get("cartId").getAsInt());
		//String id = doc.get("appointment_Id").getAsString();
		String output = crt.deleteFromCart(scart);
		return output;
	}


}
