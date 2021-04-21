package model;

import java.sql.*; 

public class Proposals {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbaget", "root", "nithya1234");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	public String insertProposals(String pcode, String title, String expBudget, String description)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into proposals (`proposalID`,`proposalCode`,`proposalTitle`,`proposalBudget`,`proposalDescription`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, pcode);
	 preparedStmt.setString(3, title);
	 preparedStmt.setDouble(4, Double.parseDouble(expBudget));
	 preparedStmt.setString(5, description);
	// execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the proposal.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	public String readProposals()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Proposal Code</th><th>Proposal Title</th>" +
	 "<th>Proposal Budget</th>" +
	 "<th>Proposal Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from proposals";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String proposalID = Integer.toString(rs.getInt("proposalID"));
	 String proposalCode = rs.getString("proposalCode");
	 String proposalTitle = rs.getString("proposalTitle");
	 String proposalBudget = Double.toString(rs.getDouble("proposalBudget"));
	 String proposalDescription = rs.getString("proposalDescription");
	 
	 // Add into the html table
	 output += "<tr><td>" + proposalCode + "</td>";
	 output += "<td>" + proposalTitle + "</td>";
	 output += "<td>" + proposalBudget + "</td>";
	 output += "<td>" + proposalDescription + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='proposals.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='proposalID' type='hidden' value='" + proposalID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the proposals.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	
	public String updateProposals(String proposalID, String pcode, String title, String expBudget, String description)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE proposals SET proposalCode=?,proposalTitle=?,proposalBudget=?,proposalDescription=? WHERE proposalID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, pcode);
	 preparedStmt.setString(2, title);
	 preparedStmt.setDouble(3, Double.parseDouble(expBudget));
	 preparedStmt.setString(4, description);
	 preparedStmt.setInt(5, Integer.parseInt(proposalID));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the proposal.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	
	public String deleteProposals(String proposalID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from proposals where proposalID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(proposalID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the proposal.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 
	 }
	

}

