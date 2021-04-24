package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import DBUtil.DBConnection;
import Model.Order;
import Model.shoppingCart;

public class cartController {
	
	double price;
	DBConnection dbObj = new DBConnection();
	
	//View Shopping Cart 
	public String viewCart() {

		String output = "";
		
		shoppingCart  cart = new shoppingCart();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>CartID</th>"
					+ "<th>ProductId</th> "+" <th>UserID</th> "+" <th>UnitPrice</th> "+" <th>Quantity</th> </tr>";

			String query = "select * from cart";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				cart.setCid(rs.getInt("cartId"));
				cart.setPid(rs.getInt("productId"));
				cart.setUid(rs.getInt("userId"));
				cart.setUnitP(rs.getDouble("unitPrice"));
				cart.setQty(rs.getInt("quantity"));
				

				// Add into the html table
				output += "<tr><td>" + cart.getCid() + "</td>";
				output += "<td>" + cart.getPid() + "</td>";
				output += "<td>" + cart.getUid() + "</td>";
				output += "<td>" + cart.getUnitP() + "</td>";
				output += "<td>" + cart.getQty() + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Cart Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//Add to Cart
	
	public String addToCart(shoppingCart cart) {

		String output = "";
		try {

			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO cart (productId,userId,unitPrice,quantity) VALUES (?,?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			Client client = new Client();
			WebResource resource = client.resource("http://localhost:8081/Product_Managemenet/ProductService/Products/"+cart.getPid());
			String response = resource.type(MediaType.TEXT_PLAIN).get(String.class);

			// binding values
			preparedStmt.setInt(1, cart.getPid());
			preparedStmt.setInt(2, cart.getUid());
			preparedStmt.setDouble(3, Double.parseDouble(response));
			preparedStmt.setInt(4, cart.getQty());
			
			
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
	
	//Update Cart
	
	public String updateCart(shoppingCart cart) {

		String output = "";

		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE cart SET productId =?,unitPrice=?,quantity=? WHERE cartId =?";
			
			String query2 = "select productPrice from products where productID = '"+cart.getPid()+"'";
			PreparedStatement preparedStmt2 = con.prepareStatement(query2); 
			ResultSet rs2 = preparedStmt2.executeQuery(query2);
			while (rs2.next()){
				
				price = rs2.getDouble("productPrice");
				
			}
			
			
			preparedStmt2.execute();
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setInt(1, cart.getPid());
			preparedStmt.setDouble(2, price);
			preparedStmt.setInt(3, cart.getQty());
			preparedStmt.setInt(4, cart.getCid());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully [ ID : "+cart.getCid()+" ]";
		} catch (Exception e) {
			output = "Error while updating the Cart Id " + cart.getCid();
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Delete From Cart
	
	public String deleteFromCart(shoppingCart cart) {
		String output = "";
		try {

			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM cart WHERE cartId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			 preparedStmt.setInt(1, cart.getCid());

			preparedStmt.execute();
			con.close();
			output = "Deleted successfully [ Cart Id : "+cart.getCid()+" ]";

		} catch (Exception e) {

			output = "Error while deleting the  Cart Id :" + cart.getCid();
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	//Filter By User Id
	public String viewCartItemsById(int uid) {

		String output = "";
		
		shoppingCart  cart = new shoppingCart();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>CartID</th>"
					+ "<th>ProductId</th> "+" <th>UnitPrice</th> "+" <th>Quantity</th> </tr>";

			String query = "select * from cart where userId = '"+uid+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				cart.setCid(rs.getInt("cartId"));
				cart.setPid(rs.getInt("productId"));
				cart.setUnitP(rs.getDouble("unitPrice"));
				cart.setQty(rs.getInt("quantity"));
				

				// Add into the html table
				output += "<tr><td>" + cart.getCid() + "</td>";
				output += "<td>" + cart.getPid() + "</td>";
				output += "<td>" + cart.getUnitP() + "</td>";
				output += "<td>" + cart.getQty() + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Cart Details By UserId.";
			System.err.println(e.getMessage());
		}

		return output;
	}


}