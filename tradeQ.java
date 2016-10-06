package dev.david;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class tradeQ {

	public static void main(String[] args) {
	    try (
		  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/stock", "root", "mysql");
	      Statement stmt = conn.createStatement();
	    ) {
		  String strSelect = "select * from tradeq";
	 //     System.out.println("The SQL query is: " + strSelect); // Echo For debugging
	     ResultSet rset = stmt.executeQuery(strSelect);
	      System.out.println("The records selected are:\nNO. Symbol\tDate1\tprice1\tfee1\ttqy\tDate2\tprice2\tfee2\tamount");
	      double amount = 0.0, gain = 0.0;
	      int rowCount = 0;
	      while(rset.next()) {   // Move the cursor to the next row
	        String symbol = rset.getString("symbol");
	        Date date1 = rset.getDate("date1");
	        double price1 = rset.getDouble("price");
	        int    qty   = rset.getInt("qty");
	        Date date2 = rset.getDate("symDate1");
	        double price2 = rset.getDouble("symPrice");
	        amount = (price2 -price1)*qty; 
	        gain += amount;
	        System.out.println((rowCount + 1) + " " + symbol + "\t" + date1 + "\t" + price1 + "\t"  + "\t"+ qty + "\t"+ date2 + "\t" + price2 + "\t"
	        	 + "\t" + amount);
	        ++rowCount;
	      }
	      System.out.println("Total records: " + rowCount + ", gain: " + gain );
	    } catch(SQLException ex) {
	      ex.printStackTrace();
	    }
	  }
	}