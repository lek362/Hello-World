package dev.david;

import java.sql.*;

public class conSql {

  public static void main(String[] args) {
    try (
	  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/stock", "root", "mysql");
      Statement stmt = conn.createStatement();
    ) {
	  String strSelect = "select * from hist";
 //     System.out.println("The SQL query is: " + strSelect); // Echo For debugging
     ResultSet rset = stmt.executeQuery(strSelect);
      System.out.println("The records selected are:\nNO. Symbol\tDate1\tprice1\tfee1\ttqy\tDate2\tprice2\tfee2\tamount");
      double amount = 0.0, gain = 0.0;
      int rowCount = 0;
      while(rset.next()) {   // Move the cursor to the next row
        String symbol = rset.getString("symbol");
        Date date1 = rset.getDate("date1");
        double price1 = rset.getDouble("price1");
        double fee1 = rset.getDouble("fee1");
        int    qty   = rset.getInt("qty");
        Date date2 = rset.getDate("date2");
        double price2 = rset.getDouble("price2");
        double fee2 = rset.getDouble("fee2");
        amount = (price2 -price1)*qty - fee1 - fee2; 
        gain += amount;
        System.out.println((rowCount + 1) + " " + symbol + "\t" + date1 + "\t" + price1 + "\t"  + fee1 + "\t"+ qty + "\t"+ date2 + "\t" + price2 + "\t"
        		+fee2 + "\t" + amount);
        ++rowCount;
      }
      System.out.println("Total records: " + rowCount + ", gain: " + gain );
    } catch(SQLException ex) {
      ex.printStackTrace();
    }
  }
}