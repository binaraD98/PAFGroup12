
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnection;
import Model.Buyer;



public class buyerController {
	
	DBConnection dbObj = new DBConnection();

	//===================== View Orders ==========================
	
	
	public String viewBuyers() {

		String output = "";
		
		Buyer buyer = new Buyer();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>BuyerID</th>" + "<th>Name</th> "
					+ "<th>Company</th> " + "<th>Address</th>" + "<th>Email</th>" + "<th>Phone</th>"  + "<th>Password</th></tr>";

			String query = "select * from buyer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				buyer.setUid(rs.getInt("buyerId"));
				buyer.setName(rs.getString("name"));
				buyer.setCompany(rs.getString("company"));
				buyer.setAddress(rs.getString("address"));
				buyer.setEmail(rs.getString("email"));
				buyer.setPhone(rs.getString("phone"));
				buyer.setPassword(rs.getString("password"));
			

				// Add into the html table
				output += "<tr><td>" + buyer.getBid() + "</td>";
				output += "<td>" + buyer.getName() + "</td>";
				output += "<td>" + buyer.getCompany() + "</td>";
				output += "<td>" + buyer.getAddress() + "</td>";
				output += "<td>" + buyer.getEmail()+ "</td>";
				output += "<td>" + buyer.getPhone()+ "</td>";
				output += "<td>" + buyer.getPassword() + "</td>";
				
				
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
	
	
	public String loginBuyers(Buyer buyer) {

		String output = "";
		
		Buyer buyerauth = new Buyer();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			

			String query = "select * from buyer WHERE email = '"+buyer.getEmail()+"' " ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				buyerauth.setPassword(rs.getString("password"));
				
			}
			con.close();
			if(buyer.getPassword().equals(buyerauth.getPassword()))
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
	
	
	
	
	
	
	
	//========================== Add In To Appointment Types =========================
	
		
	public String addBuyers(Buyer buyer) {

			String output = "";
			try {

				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " INSERT INTO buyer ( name , company ,address,email,phone ,password) VALUES (?, ?, ?, ? , ? , ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, buyer.getName());
				preparedStmt.setString(2, buyer.getCompany());
				preparedStmt.setString(3, buyer.getAddress());
				preparedStmt.setString(4, buyer.getEmail());
				preparedStmt.setString(5, buyer.getPhone());
				preparedStmt.setString(6, buyer.getPassword());
				
				
				
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
	
	
	
		//============================= Update Appointment Type ==============================
		
	
			public String updateBuyers(Buyer buyer) {

				String output = "";

				try {
					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE buyer SET name=? , company=?, address=?, email=?, phone=? , password=? WHERE buyerId = ?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values

					
					preparedStmt.setString(1, buyer.getName());
					preparedStmt.setString(2, buyer.getCompany());
					preparedStmt.setString(3, buyer.getAddress());
				    preparedStmt.setString(4, buyer.getEmail());
				    preparedStmt.setString(5, buyer.getPhone());
				    preparedStmt.setString(6, buyer.getPassword());
				    preparedStmt.setDouble(7, buyer.getBid());
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully [ ID : "+ buyer.getBid()+" ]";
				} catch (Exception e) {
					output = "Error while updating the Buyer Id " + buyer.getBid();
					System.err.println(e.getMessage());
				}
				return output;
			}


			//============================= Delete Appointment Type ==============================	
		
			
			public String deleteBuyers(Buyer buyer) {
				String output = "";
				try {

					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "DELETE FROM buyer WHERE buyerId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					 preparedStmt.setInt(1, buyer.getBid());
					//preparedStmt.setInt(4, appBean.getAppointment_Id());
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully [ Buyer Id : "+buyer.getBid()+" ]";

				} catch (Exception e) {

					output = "Error while deleting the  Buyer Id :" + buyer.getBid();
					System.err.println(e.getMessage());
				}

				return output;
			}
}
