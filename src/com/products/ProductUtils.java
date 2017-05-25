package com.products;

import java.util.*;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class ProductUtils{

	public static List<String> getVendors(){
		List<String> vendorList = new ArrayList<String>();
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select distinct(vendor) from field_map_config");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				vendorList.add(rs.getString("vendor"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return vendorList;
	}
	
}