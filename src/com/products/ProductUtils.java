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
	int 	iTotalRecords;
	
	int 	iTotalDisplayRecords;
	
	String 	sEcho;
	
	String sColumns;
	
	List<Product> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<Product> getAaData() {
		return aaData;
	}

	public void setAaData(List<Product> aaData) {
		this.aaData = aaData;
	}
}