package com.products;

  import java.sql.*;
   import java.util.*;


   public class ConnectionManager {

      static Connection con;
      static String url;
            
      public static Connection getConnection()
      {
        
         try
         {
            String url = "jdbc:odbc:" + "DataSource"; 
            // assuming "DataSource" is your DataSource name

           // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");
            try
            {            	
               con = DriverManager.getConnection("jdbc:mysql://benderme.serveblog.net:3306/products","victor","victor"); 
                								
            // assuming your SQL Server's	username is "username"               
            // and password is "password"
                 
            }
            
            catch (SQLException ex)
            {
               ex.printStackTrace();
            }
         }

         catch(ClassNotFoundException e)
         {
            System.out.println(e);
         }

      return con;
}
   }
