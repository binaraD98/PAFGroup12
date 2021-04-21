package com;

import model.Proposals;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Proposals")
public class ProposalService {
	Proposals proposalObj = new Proposals();


	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProposals() 
	 { 
	 return proposalObj.readProposals(); 
	 }

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProposals(@FormParam("proposalCode") String proposalCode, 
	 @FormParam("proposalTitle") String proposalTitle, 
	 @FormParam("proposalBudget") String proposalBudget, 
	 @FormParam("proposalDescription") String proposalDescription) 
	{ 
	 String output = proposalObj.insertProposals(proposalCode,proposalTitle,proposalBudget,proposalDescription); 
	return output; 
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProposals(String proposalData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject proposalObject = new JsonParser().parse(proposalData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String proposalID = proposalObject.get("proposalID").getAsString(); 
	 String proposalCode = proposalObject.get("proposalCode").getAsString(); 
	 String proposalTitle = proposalObject.get("proposalTitle").getAsString(); 
	 String proposalBudget = proposalObject.get("proposalBudget").getAsString(); 
	 String proposalDescription = proposalObject.get("proposalDescription").getAsString(); 
	 String output = proposalObj.updateProposals(proposalID,proposalCode,proposalTitle,proposalBudget, proposalDescription); 
	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProposals(String proposalData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(proposalData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <proposalID>
	 String proposalID = doc.select("proposalID").text(); 
	 String output = proposalObj.deleteProposals(proposalID); 
	return output; 
	}

	
}

