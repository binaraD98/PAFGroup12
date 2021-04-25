package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import DBUtil.DBConnection;
import Model.Funds;

public class fundController {
	
	DBConnection dbObj = new DBConnection();
	
	 
	//........................View Funds Function.....................................
	
	
	public String ViewFunds() {
		
		String output = "";
		
		Funds funds = new Funds();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			//Table to display the Funds
			output = "<table border=\"1\"><tr><th>Fund ID</th>"
					+ "<th>Proposal ID</th><th>Amount</th>" + "<th>Description</th>"+"</tr>";
			
			String query = "select * from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				funds.setFundID(rs.getInt("fundID"));
				funds.setPropID(rs.getInt("propID"));
				funds.setAmount(rs.getDouble("amount"));
				funds.setDescription(rs.getString("description"));
				
				
				output += "<tr><td>" + funds.getFundID() + "</td>";
				output += "<td>" + funds.getPropID() + "</td>";
				output += "<td>" + funds.getAmount() + "</td>";
				output += "<td>" + funds.getDescription() + "</td>";
					
			}
			con.close();
			output += "</table>";
			
		}catch(Exception e){
			output = "Error while reading Details.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}	
	
	//..................Delete Function Implementation...............................
	
	
	public String deleteFunds(Funds funds) {
		
		String output = "";
		
		try {

			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM funds WHERE fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			 preparedStmt.setInt(1, funds.getFundID());
			
			preparedStmt.execute();
			con.close();
			output = "[ Fund Id : "+funds.getFundID()+" ] Deleted successfully";

		} catch (Exception e) {

			output = "Error while deleting the  Fund reffered to ID :" + funds.getFundID();
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	//.......................Releasing New Funds Function.............................
	
	public String addFunds(Funds funds) {
		
		String output = "";
		try {

			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create the prepared statement
			String query = " INSERT INTO funds (propID, amount, description) VALUES (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setInt(1, funds.getPropID());
			preparedStmt.setDouble(2, funds.getAmount());
			preparedStmt.setString(3, funds.getDescription());
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
	}
	
	
	//============================= Update Fund Info Function ==============================
	
	
	public String updateFunds(Funds funds) {

		String output = "";

		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// creating a prepared statement
			String query = "UPDATE funds SET propID=?,amount=?,description=? WHERE fundID =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			

			preparedStmt.setInt(1, funds.getPropID());
			preparedStmt.setDouble(2,funds.getAmount());
			preparedStmt.setString(3, funds.getDescription());
			preparedStmt.setInt(4, funds.getFundID());
			
			
			preparedStmt.execute();
			con.close();
			output = "[ ID : "+funds.getFundID()+" ] Updated successfully ";
			
		} catch (Exception e) {
			output = "Error while updating the Fund info reffered to ID " + funds.getFundID();
			System.err.println(e.getMessage());
		}
		return output;
	}

}