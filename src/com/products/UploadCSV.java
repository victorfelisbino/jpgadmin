package com.products;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.opencsv.CSVReader;

import java.io.File;

/**
 * Servlet implementation class LoginServlet
 */
public class UploadCSV extends HttpServlet {

	public static List<String> csvFields;
	public static List<String> productsFields;
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
				           throws ServletException, java.io.IOException {
	
		try{
			HttpSession session = request.getSession(true);
			Part filePart = request.getPart("file");
			InputStream fileContent = filePart.getInputStream();
			System.out.println(fileContent);
			CSVReader csvReader = new CSVReader(new InputStreamReader(fileContent));
			String [] nextLine = csvReader.readNext();
			csvFields = new ArrayList<String>();
			HashMap<String,Integer> csvFieldsMap = new HashMap<String,Integer>();
			Integer count = 0;
			String sql = "insert into products ( ";
			for(String field : getproductsFields()){
				if(count == 0){
					sql += field;
				}else{
					sql += ", "+field;
				}
				count++;
			}
			sql += ") values(";
			while(count > 0){
				if(count > 1){
					sql+="?,";
				}else{
					sql+= "?)";
				}
				count--;
			}
			System.out.println(sql);
			count = 0;
			for(String field : nextLine){
				if(!field.isEmpty()){
					csvFields.add(field);
					csvFieldsMap.put(field, count);
					count++;
				}
			}
			//csvFields = new ArrayList<String>(Arrays.asList(nextLine));
		    
		     //session.setAttribute("csvFields",csvFields); 
		     //session.setAttribute("csvReader", csvReader);
		     HashMap<String,String> mappingMap = getExistingMap("Petra");
		     Connection con= ConnectionManager.getConnection();
		     PreparedStatement ps=con.prepareStatement(sql);
		     while ((nextLine = csvReader.readNext()) != null) {
		    	 // need to check sku first and decide to update row or insert new
		    	 
	    		 ps.setString(1, csvFieldsMap.get(mappingMap.get("VENDOR_SKU")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("VENDOR_SKU"))]: ""); //VENDOR SKU 
	    		 ps.setString(2, csvFieldsMap.get(mappingMap.get("DIST_SKU")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("DIST_SKU"))]: ""); //DIST SKU
	    		 ps.setString(3, "PETRA"); //VENDOR
	    		 ps.setString(4, csvFieldsMap.get(mappingMap.get("SHORT_DESC")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SHORT_DESC"))]: ""); //SHORT DESC
				 ps.setString(5, csvFieldsMap.get(mappingMap.get("AVAILABLE")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("AVAILABLE"))] : ""); //AVAILABLE
				 ps.setDouble(6, Double.valueOf(csvFieldsMap.get(mappingMap.get("DIST_COST")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("DIST_COST"))] : "")); //DIST COST
				 ps.setString(7, csvFieldsMap.get(mappingMap.get("BRAND_NAME")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("BRAND_NAME"))] : ""); //BRAND NAME, 
				 ps.setString(8, csvFieldsMap.get(mappingMap.get("WEIGHT_UNPACKED")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("WEIGHT_UNPACKED"))] : ""); //WEIGHT-UNPACKED, 
				 ps.setString(9, csvFieldsMap.get(mappingMap.get("PRODUCT_CLASS")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("PRODUCT_CLASS"))] : ""); //PRODUCT CLASS, 
				 ps.setString(10, csvFieldsMap.get(mappingMap.get("UPC")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("UPC"))] : ""); //UPC, 
				 ps.setString(11, csvFieldsMap.get(mappingMap.get("LONG_DESC")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("LONG_DESC"))] : ""); //LONG DESC, 
				 ps.setString(12, csvFieldsMap.get(mappingMap.get("KEYWORDS")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("KEYWORDS"))] : ""); //KEYWORDS, 
				 ps.setString(13, csvFieldsMap.get(mappingMap.get("SPECS")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SPECS"))] : ""); //SPECS, 
				 ps.setString(14, csvFieldsMap.get(mappingMap.get("SUBCATEGORY")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SUBCATEGORY"))] : ""); //SUBCATEGORY, 
				 ps.setString(15, csvFieldsMap.get(mappingMap.get("NOTES")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("NOTES"))] : ""); //NOTES, 
				 ps.setString(16, csvFieldsMap.get(mappingMap.get("REFURB")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("REFURB"))] : ""); //REFURB, 
				 ps.setDouble(17, Double.valueOf(csvFieldsMap.get(mappingMap.get("LENGTH")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("LENGTH"))] : "")); //LENGTH, 
				 ps.setDouble(18, Double.valueOf(csvFieldsMap.get(mappingMap.get("WIDTH")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("WIDTH"))] : "")); //WIDTH, 
				 ps.setDouble(19, Double.valueOf(csvFieldsMap.get(mappingMap.get("HEIGHT")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("HEIGHT"))] : "")); //HEIGHT, 
				 ps.setDouble(20, Double.valueOf(csvFieldsMap.get(mappingMap.get("MAP")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("MAP"))] : "")); //MAP, 
				 ps.setString(21, csvFieldsMap.get(mappingMap.get("RETURNABLE")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("RETURNABLE"))] : ""); //RETURNABLE, 
				 ps.setDouble(22, Double.valueOf(csvFieldsMap.get(mappingMap.get("ESTIMATED_SHIP_WEIGHT")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("ESTIMATED_SHIP_WEIGHT"))] : "")); //ESTIMATED SHIP WEIGHT, 
				 ps.setString(23, csvFieldsMap.get(mappingMap.get("NOTES2")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("NOTES2"))] : ""); //NOTES2, 
				 ps.setString(24, csvFieldsMap.get(mappingMap.get("SUBCATEGORY2")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SUBCATEGORY2"))] : ""); //SUBCATEGORY2, 
				 ps.setString(25, csvFieldsMap.get(mappingMap.get("SUBCATEGORY3")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SUBCATEGORY3"))] : ""); //SUBCATEGORY3, 
				 ps.setDouble(26, Double.valueOf(csvFieldsMap.get(mappingMap.get("MSRP")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("MSRP"))] : "")); //MSRP, 
				 if(csvFieldsMap.get(mappingMap.get("PO_ETA_DATE")) != null && !nextLine[csvFieldsMap.get(mappingMap.get("PO_ETA_DATE"))].isEmpty()){
					 try{
						 ps.setDate(27, java.sql.Date.valueOf(nextLine[csvFieldsMap.get(mappingMap.get("PO_ETA_DATE"))]));//PO ETA DATE,
					 }catch(Exception poeta){
						 ps.setDate(27, null);//PO ETA DATE,
					 }
				 }else{
					 ps.setDate(27, null);//PO ETA DATE,
				 }
				 ps.setString(28, csvFieldsMap.get(mappingMap.get("WARRANTY")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("WARRANTY"))] : ""); //WARRANTY, 
				 ps.setString(29, csvFieldsMap.get(mappingMap.get("IMAGE_URL")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("IMAGE_URL"))] : ""); //IMAGE URL,
				 ps.setFloat(30, 0); //AMAZON COST TO SELL, 
				 ps.setFloat(31, 0); //PERCENT MARKUP, 
				 ps.setFloat(32, 0); //PROFIT, 
				 ps.setFloat(33, 0); //AMAZON SELLING PRICE, 
				 ps.setString(34, csvFieldsMap.get(mappingMap.get("SELLING_ON_AMAZON")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SELLING_ON_AMAZON"))] : ""); //SELLING ON AMAZON, 
				 ps.setString(35, csvFieldsMap.get(mappingMap.get("DROP_SHIP")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("DROP_SHIP"))] : ""); //DROP SHIP, 
				 ps.setString(36, csvFieldsMap.get(mappingMap.get("SELL_PRODUCT")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("SELL_PRODUCT"))] : ""); //SELL PRODUCT, 
				 ps.setString(37, csvFieldsMap.get(mappingMap.get("PRODUCT_DISCONTINUED")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("PRODUCT_DISCONTINUED"))] : ""); //PRODUCT DISCONTINUED, 
				 ps.setString(38, csvFieldsMap.get(mappingMap.get("MODIFIED")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("MODIFIED"))] : ""); //MODIFIED, 
				 ps.setDate(39, java.sql.Date.valueOf(java.time.LocalDate.now())); //DATE MODIFIED, 
				 ps.setString(40, csvFieldsMap.get(mappingMap.get("REVIEW_NEEDED")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("REVIEW_NEEDED"))] : ""); //REVIEW NEEDED, 
				 ps.setString(41, csvFieldsMap.get(mappingMap.get("MODIFIED_FIELDS")) != null ? nextLine[csvFieldsMap.get(mappingMap.get("MODIFIED_FIELDS"))] : ""); //MODIFIED FIELDS
				 ps.executeUpdate();
		     }
		     response.sendRedirect("fileUpload.jsp"); 
		}catch (Exception theException){
			System.out.println("csv"+theException); 
			theException.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {
		HttpSession session = request.getSession(true);
		String newExisting = request.getParameter("fieldMapping");
		if(newExisting.equals("New")){
			saveNewMapping(request);
		}else{
			//updateMapping(request);
		}
		
	}
	public static List<String> getcsvFields(){
		return csvFields;
	}
	public static List<String> getproductsFields(){
		productsFields = new ArrayList<String>();
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from products limit 1");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount(); //number of column
			String columnName[] = new String[count];

			for (int i = 1; i <= count; i++)
			{
			   columnName[i-1] = metaData.getColumnLabel(i);
			   //System.out.println(columnName[i-1]);
			   if(!columnName[i-1].equals("ID")){
				   productsFields.add(columnName[i-1]);
			   }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(productsFields);
		return productsFields;
	}
	public static void saveNewMapping(HttpServletRequest request){
		try{
			String vendor = request.getParameter("vendorName");
			Connection con= ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into field_map_config(vendor,vendor_column,products_column) values(?,?,?)");
			for(String field : request.getParameterMap().keySet()){
				System.out.println(field);
				ps.setString(1,vendor);
				ps.setString(2,field);
				ps.setString(3,request.getParameter(field));
				ps.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static HashMap<String,String> getExistingMap(String mapping){
		HashMap<String,String> mappingMap = new HashMap<String,String>();
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select vendor_column,products_column,id from field_map_config where vendor = \'"+mapping+"\'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				mappingMap.put(rs.getString("products_column"),rs.getString("vendor_column"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mappingMap;
	}
}