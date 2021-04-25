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

import Controller.fundController;
import Model.Funds;



@Path("/funds")
public class fundServices {
	
	fundController funds = new fundController();
	
	//.................Viewing Funds...............................
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readAllFunds() {
			return funds.ViewFunds();
		}
		
		
		
		//...................Adding Funds...........................
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		
		public String enterFund(String FundData) {
			// Converting input objects to json objects
			
			JsonObject djsonObj = new JsonParser().parse(FundData).getAsJsonObject() ;
		
			Funds fund = new Funds();
			
			
			fund.setPropID(djsonObj .get("propID").getAsInt());
			fund.setAmount(djsonObj .get("amount").getAsDouble());
			fund.setDescription(djsonObj .get("description").getAsString());

			// Read the values from the JSON object
		
			String output = funds.addFunds(fund);
			return output;

		}
		
		
		
		
		//................................Deleting Funds..................................
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteFund(String FundData) {
			
			JsonObject doc = new JsonParser().parse(FundData).getAsJsonObject();


			Funds fund = new Funds();
			
			// Read the values from the element <fundID>
	    	fund.setFundID(doc.get("fundID").getAsInt());
		
			String output = funds.deleteFunds(fund);
			return output;
		}
		
		
		
		
		//....................Updating Funds ...........................................
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateFunds(String FundData) {

			
			JsonObject djsonObj = new JsonParser().parse(FundData).getAsJsonObject();

			
			Funds fund = new Funds();

			fund.setFundID(djsonObj.get("fundID").getAsInt());
			fund.setPropID(djsonObj.get("propID").getAsInt());
			fund.setAmount(djsonObj.get("amount").getAsDouble());
			fund.setDescription(djsonObj.get("description").getAsString());

			
			String output = funds.updateFunds(fund);
			return output;
		}
		

}

	
	
	
	
	