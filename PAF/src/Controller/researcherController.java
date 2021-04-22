package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnection;
import Model.Admin;
import Model.Researcher;


public class researcherController {
	
	DBConnection dbObj = new DBConnection();

	
	//View Researcher Details
	
	public String viewResearchers() {

		String output = "";
		
		Researcher  researcher = new Researcher();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>USerID</th>"
					+ "<th>Password</th> " + "<th>Address</th>" + "<th>Email</th>" + "<th>Phone</th></tr>";

			String query = "select * from researcher";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

			researcher.setUid(rs.getInt("userId"));
			researcher.setPassword(rs.getString("password"));
			researcher.setAddress(rs.getString("address"));
			researcher.setEmail(rs.getString("email"));
			researcher.setPhone(rs.getString("phone"));
			

			// Add into the html table
			output += "<tr><td>" + researcher.getUid() + "</td>";
			output += "<td>" + researcher.getPassword() + "</td>";
			output += "<td>" + researcher.getAddress() + "</td>";
			output += "<td>" + researcher.getEmail()+ "</td>";
			output += "<td>" + researcher.getPhone()+ "</td>";
				
			}
			
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the userTypes Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//login researcher validate
	
	public String loginResearchers(Researcher researcher) {

		String output = "";
		
		Researcher researcherauth = new Researcher();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			

			String query = "select * from researcher WHERE email = '"+researcher.getEmail()+"' " ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				researcherauth.setPassword(rs.getString("password"));
				
			}
			con.close();
			if(researcher.getPassword().equals(researcherauth.getPassword()))
			{
				
				output = "login succesfull";
			}
			else {
				output = "login unsuccesfull";
			}
			
			
		} catch (Exception e) {
			output = "Error while reading the userTypes Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//Add Researcher details
	
	public String addResearchers(Researcher researcher) {

			String output = "";
			try {

				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " INSERT INTO researcher ( password,address,email,phone) VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, researcher.getPassword());
				preparedStmt.setString(2, researcher.getAddress());
				preparedStmt.setString(3, researcher.getEmail());
				preparedStmt.setString(4, researcher.getPhone());
				
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
	
	
	//Update Researcher details
		
	public String updateResearchers(Researcher researcher) {

				String output = "";

				try {
					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE researcher SET password=? , address=?, email=?, phone=?  WHERE userId = ?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, researcher.getPassword());
					preparedStmt.setString(2, researcher.getAddress());
				    preparedStmt.setString(3, researcher.getEmail());
				    preparedStmt.setString(4, researcher.getPhone());
				    preparedStmt.setDouble(5, researcher.getUid());
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully [ ID : "+researcher.getUid()+" ]";
				} catch (Exception e) {
					output = "Error while updating the User Id " + researcher.getUid();
					System.err.println(e.getMessage());
				}
				return output;
			}


	//Delete Researcher
		
	public String deleteResearchers(Researcher researcher) {
				String output = "";
				try {

					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "DELETE FROM researcher WHERE userId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					 preparedStmt.setInt(1, researcher.getUid());
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully [ User Id : "+researcher.getUid()+" ]";

				} catch (Exception e) {

					output = "Error while deleting the  User Id :" + researcher.getUid();
					System.err.println(e.getMessage());
				}

				return output;
			}
}


