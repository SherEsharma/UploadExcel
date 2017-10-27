package com.xenture.connection;

import java.sql.*;

public class DConnection {

	private static final String Driver="com.mysql.jdbc.Driver";
    //private static final String uri="jdbc:mysql://localhost:3306/demotest";
	private static final String uri="jdbc:mysql://localhost:3306/demo";
    private static final String username="root";
    private static final String pass="";

   public static Connection getConnection()
   {
	   Connection conn=null;
	   try
	   {
	   Class.forName(Driver);
	   conn=DriverManager.getConnection(uri,username,pass);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   return conn;

   }
}