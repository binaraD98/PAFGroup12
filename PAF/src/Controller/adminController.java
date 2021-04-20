package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnection;
import Model.Admin;
import Model.Buyer;
import Model.Researcher;





public class adminController  {
	
	DBConnection dbObj = new DBConnection();

	//===================== View Orders ==========================
	
	
//	public String viewAdmins() {
//
//		String output = "";
//		
//		
//		Admin admin = new Admin();
//		
//		try {
//			Connection con = dbObj.connect();
//			if (con == null) {
//				return "Error while connecting to the database for reading.";
//			}
//			// Prepare the html table to be displayed
//			output = "<table border=\"1\"><tr><th>AdminID</th>" + "<th>Name</th> "
//					+ "<th>Company</th> " + "<th>Address</th>" + "<th>Email</th>" + "<th>Phone</th>"  + "<th>Password</th>"  + "<th>Amount</th>"  +  "<th>Lisencenum</th></tr>";
//
//			String query = "select * from admin";
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//
//			// iterate through the rows in the result set
//			while (rs.next()) {
//
//				admin.setAid(rs.getInt("adminId"));
//				admin.setName(rs.getString("name"));
//				admin.setCompany(rs.getString("company"));
//				admin.setAddress(rs.getString("address"));
//				admin.setEmail(rs.getString("email"));
//				admin.setPhone(rs.getString("phone"));
//				admin.setPassword(rs.getString("password"));
//				admin.setAmount(rs.getString("amount"));
//				admin.setlisencenum(rs.getString("lisencenum"));
//			
//
//				// Add into the html table
//				output += "<tr><td>" + admin.getAid() + "</td>";
//				output += "<td>" + admin.getName() + "</td>";
//				output += "<td>" + admin.getCompany() + "</td>";
//				output += "<td>" + admin.getAddress() + "</td>";
//				output += "<td>" + admin.getEmail()+ "</td>";
//				output += "<td>" + admin.getPhone()+ "</td>";
//				output += "<td>" + admin.getPassword() + "</td>";
//				output += "<td>" + admin.getAmount() + "</td>";
//				output += "<td>" + admin.getLisencenum() + "</td>";
//				
//				
//			}
//			con.close();
//			// Complete the html table
//			output += "</table>";
//
//		} catch (Exception e) {
//			output = "Error while reading the userTypes Details.";
//			System.err.println(e.getMessage());
//		}
//
//		return output;
//	}
//	
//	
	
	
	public String viewAdmins() {

		String output = "";
		
		Admin admin = new Admin();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>AdminID</th>" + "<th>Name</th> "
					+ "<th>Company</th> " + "<th>Address</th>" + "<th>Email</th>" + "<th>Phone</th>"  + "<th>Password</th>"  + "<th>Amount</th>"  +  "<th>Lisencenum</th></tr>";

			String query = "select * from admin";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				admin.setUid(rs.getInt("userId"));
				admin.setName(rs.getString("name"));
				admin.setCompany(rs.getString("company"));
				admin.setAddress(rs.getString("address"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				admin.setPassword(rs.getString("password"));
				admin.setAmount(rs.getString("amount"));
				admin.setlisencenum(rs.getString("lisencenum"));

				// Add into the html table
				output += "<tr><td>" + admin.getUid() + "</td>";
				output += "<td>" + admin.getName() + "</td>";
				output += "<td>" + admin.getCompany() + "</td>";
				output += "<td>" + admin.getAddress() + "</td>";
				output += "<td>" + admin.getEmail()+ "</td>";
				output += "<td>" + admin.getPhone()+ "</td>";
				output += "<td>" + admin.getPassword() + "</td>";
				output += "<td>" + admin.getAmount() + "</td>";
				output += "<td>" + admin.getLisencenum() + "</td>";
				
				
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
	
	
	
	public String loginAdmins(Admin admin) {

		String output = "";
		
		Admin adminauth = new Admin();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			

			String query = "select * from admin WHERE email = '"+admin.getEmail()+"' " ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				adminauth.setPassword(rs.getString("password"));
				
			}
			con.close();
			if(admin.getPassword().equals(adminauth.getPassword()))
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
	
		
	public String addAdmins(Admin admin) {

			String output = "";
			try {

				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " INSERT INTO admin ( name , company ,address,email,phone ,password,amount,lisencenum) VALUES (?, ?, ?, ? , ? , ?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, admin.getName());
				preparedStmt.setString(2, admin.getCompany());
				preparedStmt.setString(3, admin.getAddress());
				preparedStmt.setString(4, admin.getEmail());
				preparedStmt.setString(5, admin.getPhone());
				preparedStmt.setString(6, admin.getPassword());
				preparedStmt.setString(7, admin.getAmount());
				preparedStmt.setString(8, admin.getLisencenum());
				
				
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
		
	
//			public String updateAdmins(Admin admin) {
//
//				String output = "";
//
//				try {
//					Connection con = dbObj.connect();
//					if (con == null) {
//						return "Error while connecting to the database for updating.";
//					}
//					// create a prepared statement
//					String query = "UPDATE admin SET name=? , company=?, address=?, email=?, phone=? , password=? , amount =? , lisencenum=? WHERE adminId = ?";
//					PreparedStatement preparedStmt = con.prepareStatement(query);
//
//					// binding values
//
//					
//					preparedStmt.setString(1, admin.getName());
//					preparedStmt.setString(2, admin.getCompany());
//					preparedStmt.setString(3, admin.getAddress());
//				    preparedStmt.setString(4, admin.getEmail());
//				    preparedStmt.setString(5, admin.getPhone());
//				    preparedStmt.setString(6, admin.getPassword());
//				    preparedStmt.setString(7, admin.getAmount());
//				    preparedStmt.setString(8, admin.getLisencenum());
//				    preparedStmt.setDouble(9, admin.getAid());
//					
//					
//					// execute the statement
//					preparedStmt.execute();
//					con.close();
//					output = "Updated successfully [ ID : "+ admin.getAid()+" ]";
//				} catch (Exception e) {
//					output = "Error while updating the Admin Id " + admin.getAid();
//					System.err.println(e.getMessage());
//				}
//				return output;
//			}
//
//
//			//============================= Delete Appointment Type ==============================	
//		
//			
//			public String deleteAdmins(Admin admin) {
//				String output = "";
//				try {
//
//					Connection con = dbObj.connect();
//					if (con == null) {
//						return "Error while connecting to the database for deleting.";
//					}
//
//					// create a prepared statement
//					String query = "DELETE FROM admin WHERE adminId=?";
//					PreparedStatement preparedStmt = con.prepareStatement(query);
//
//					// binding values
//					 preparedStmt.setInt(1, admin.getAid());
//					//preparedStmt.setInt(4, appBean.getAppointment_Id());
//					// execute the statement
//					preparedStmt.execute();
//					con.close();
//					output = "Deleted successfully [ Admin Id : "+admin.getAid()+" ]";
//
//				} catch (Exception e) {
//
//					output = "Error while deleting the  Admin Id :" + admin.getAid();
//					System.err.println(e.getMessage());
//				}
//
//				return output;
//			}
	
	
	
	
	
	public String updateAdmins(Admin admin){

		String output = "";

		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE admin SET name=? , company=?, address=?, email=?, phone=? , password=? , amount =? , lisencenum=?   WHERE userId = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			
			
			preparedStmt.setString(1, admin.getName());
			preparedStmt.setString(2, admin.getCompany());
			preparedStmt.setString(3, admin.getAddress());
		    preparedStmt.setString(4, admin.getEmail());
		    preparedStmt.setString(5, admin.getPhone());
		    preparedStmt.setString(6, admin.getPassword());
		    preparedStmt.setString(7, admin.getAmount());
		    preparedStmt.setString(8, admin.getLisencenum());
		    preparedStmt.setDouble(9, admin.getUid());
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully [ ID : "+admin.getUid()+" ]";
		} catch (Exception e) {
			output = "Error while updating the User Id " + admin.getUid();
			System.err.println(e.getMessage());
		}
		return output;
	}


	//============================= Delete Appointment Type ==============================	

	
	public String deleteAdmins(Admin admin) {
		String output = "";
		try {

			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM admin WHERE userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			 preparedStmt.setInt(1, admin.getUid());
			//preparedStmt.setInt(4, appBean.getAppointment_Id());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully [ User Id : "+admin.getUid()+" ]";

		} catch (Exception e) {

			output = "Error while deleting the  User Id :" + admin.getUid();
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	
}
