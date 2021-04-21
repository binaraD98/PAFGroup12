package Controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Order;
import DBUtil.DBConnection;




public class orderController {
	
	double price,unitPrice,total;
	int quantity,cart_Id;
	
	DBConnection dbObj = new DBConnection();

	//===================== View Orders ==========================
	
	
	public String viewOrders() {

		String output = "";
		
		Order  order = new Order();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>OrderID</th>"
					+ "<th>CartID</th> "+" <th>UserID</th>  "+" <th>Name</th> "+" <th> Adderss</th>" + "<th>Email</th> "+"<th>Phone</th> "+" <th>Total</th></tr>";

			String query = "select * from orders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				order.setOid(rs.getInt("orderId"));
				order.setCid(rs.getInt("cartId"));
				order.setUid(rs.getInt("userId"));
				order.setName(rs.getString("name"));
				order.setAddress(rs.getString("address"));
				order.setEmail(rs.getString("email"));
				order.setPhone(rs.getString("phone"));
				order.setTotal(rs.getDouble("total"));

				// Add into the html table
				output += "<tr><td>" + order.getOid() + "</td>";
				output += "<td>" + order.getCid() + "</td>";
				output += "<td>" + order.getUid() + "</td>";
				output += "<td>" + order.getName() + "</td>";
				output += "<td>" + order.getAddress() + "</td>";
				output += "<td>" + order.getEmail()+ "</td>";
				output += "<td>" + order.getPhone()+ "</td>";
				output += "<td>" + order.getTotal()+ "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Order Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	//========================== Add Orders =========================
	
		
	public String addOrders(Order order) {

			String output = "";
			try {

				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " INSERT INTO orders (cartId,userId,name,address,email,phone,total) VALUES (?, ?, ?, ?, ?, ?, ?)";
				
				
				String query2 = "select quantity , unitPrice from cart order by cartId desc limit 1";
				PreparedStatement preparedStmt2 = con.prepareStatement(query2); 
				ResultSet rs = preparedStmt2.executeQuery(query2);
				while (rs.next()){
					
					 quantity = (rs.getInt("quantity"));
			         unitPrice = (rs.getDouble("unitPrice")); 	
				}
				total = quantity * unitPrice;
				preparedStmt2.execute();
				
				
			 	String query3 = "select cartId from cart order by cartId desc limit 1";
				PreparedStatement preparedStmt3 = con.prepareStatement(query3); 
				ResultSet rs3 = preparedStmt3.executeQuery(query3);
				while (rs3.next()){
					
					cart_Id = rs3.getInt("cartId");
				}
				
				preparedStmt3.execute();
				
				
				
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1,cart_Id);
				preparedStmt.setInt(2, order.getUid());
				preparedStmt.setString(3, order.getName());
				preparedStmt.setString(4, order.getAddress());
				preparedStmt.setString(5, order.getEmail());
				preparedStmt.setString(6, order.getPhone());
				preparedStmt.setDouble(7, total);
				
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
	
	
	
		//============================= Update Orders ==============================
		
	
			public String updateOrders(Order order) {

				String output = "";

				try {
					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE orders SET name=?,address=?,email=?,phone=? WHERE orderId =?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					
					preparedStmt.setString(1, order.getName());
					preparedStmt.setString(2, order.getAddress());
					preparedStmt.setString(3, order.getEmail());
					preparedStmt.setString(4, order.getPhone());
					preparedStmt.setInt(5, order.getOid());
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully [ ID : "+order.getOid()+" ]";
				} catch (Exception e) {
					output = "Error while updating the Order Id " + order.getOid();
					System.err.println(e.getMessage());
				}
				return output;
			}


			//============================= Delete Orders ==============================	
		
			
			public String deleteOrders(Order order) {
				String output = "";
				try {

					Connection con = dbObj.connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "DELETE FROM orders WHERE orderId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					 preparedStmt.setInt(1, order.getOid());

					preparedStmt.execute();
					con.close();
					output = "Deleted successfully [ Order Id : "+order.getOid()+" ]";

				} catch (Exception e) {

					output = "Error while deleting the  Order Id :" + order.getOid();
					System.err.println(e.getMessage());
				}

				return output;
			}
			
				
			
			
}