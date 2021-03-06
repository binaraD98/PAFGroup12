package model;

import java.sql.*;

public class Product {

	// method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "nikeshal@123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProduct(String code, String name, String price, String inventor, String type) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into products (`productID`,`productCode`,`productName`,`productPrice`,`productInventor`,`productType`)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, inventor);
			preparedStmt.setString(6, type);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Product..";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//Read Products
	public String readProducts() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" + "<th>Product Price</th>"
					+ "<th>Product Inventor</th>"+ "<th>Product Type</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String productID = Integer.toString(rs.getInt("productID"));
				String productCode = rs.getString("productCode");
				String productName = rs.getString("productName");
				String productPrice = Double.toString(rs.getDouble("productPrice"));
				String productInventor = rs.getString("productInventor");
				String productType = rs.getString("productType");
				// Add into the html table
				output += "<tr><td>" + productCode + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + productPrice + "</td>";
				output += "<td>" + productInventor + "</td>";
				output += "<td>" + productType + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='products.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='productID' type='hidden' value='" + productID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the products..";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update Products
	public String updateProduct(String ID, String code, String name, String price, String inventor, String type)
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE products SET productCode=?,productName=?,productPrice=?,productInventor=?,productType=?WHERE productID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, code); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setDouble(3, Double.parseDouble(price)); 
	 preparedStmt.setString(4, inventor); 
	 preparedStmt.setString(5,type); 
	 preparedStmt.setInt(6, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the Product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	
    //Delete Products
	public String deleteProduct(String productID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from products where productID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Product..";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//==========================to read price of a particular product======================================
	
			public String readUnitPrice(String pid){	 
				String output = "";
				//int id = Integer.parseInt(pro_ID);
				
				try{
					 Connection con = connect();
					 
					if (con == null){
						return "Error while connecting to the database for reading."; 
					}
							
					//Query to execute
					String query = "select productPrice from products where productID = '"+pid+"'";
					//creating the prepared statement
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					//preparedStmt.setInt(1, Integer.parseInt(pro_ID));
					
					//Retrieving the values to a result set
					ResultSet rs = preparedStmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next()){
						
						String price = Double.toString(rs.getDouble("productPrice"));				
						output = price;				
						
					}
					con.close();
				}
				
				catch (Exception e)
				{
					output = "Error while reading the product unit price.";
					System.err.println(e.getMessage());
				}
				return output;
			}

		

	
	
	
}
