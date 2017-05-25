package com.products;

import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDao{
	static Connection currentCon = null;
	static ResultSet rs = null;  
	
	public static UserBean login(UserBean bean) {

		//preparing some objects for connection 
		Statement stmt = null;    

		String username = bean.getUsername();    
		String password = bean.getPassword();   

		String searchQuery = "select * from users where username='"
							+ username
							+ "' AND password='"
							+ password
							+ "'";
    
  // "System.out.println" prints in the console; Normally used to trace the process
  //System.out.println("Your user name is " + username);          
  //System.out.println("Your password is " + password);
  //System.out.println("Query: "+searchQuery);
    
  try 
  {
     //connect to DB 
 currentCon = ConnectionManager.getConnection();
 stmt=currentCon.createStatement();
 rs = stmt.executeQuery(searchQuery);	        
 boolean more = rs.next();
   
 // if user does not exist set the isValid variable to false
 if (!more) 
 {
    System.out.println("Sorry, you are not a registered user! Please sign up first");
    bean.setValid(false);
 } 
    
 //if user exists set the isValid variable to true
 else if (more) 
 {
    String firstName = rs.getString("FirstName");
    String lastName = rs.getString("LastName");

    System.out.println("Welcome " + firstName);
        bean.setFirstName(firstName);
        bean.setLastName(lastName);
        bean.setValid(true);
     }
  } 

  catch (Exception ex) 
  {
     System.out.println("Log In failed: An Exception has occurred! " + ex);
  } 
    
  //some exception handling
      finally 
      {
         if (rs != null)	{
            try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }
	
         if (stmt != null) {
            try {
               stmt.close();
            } catch (Exception e) {}
               stmt = null;
            }
	
         if (currentCon != null) {
            try {
               currentCon.close();
            } catch (Exception e) {
            }

            currentCon = null;
         }
      }

return bean;
	
      }	
	public static int save(UserBean u){
		int status=0;
		try{
			Connection con= ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into users(FirstName,LastName,username,password,email) values(?,?,?,?,?)");
			ps.setString(1,u.getFirstName());
			ps.setString(2,u.getLastName());
			ps.setString(3,u.getUsername());
			ps.setString(4,u.getPassword());
			ps.setString(5,u.getEmail());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(UserBean u){
		int status=0;
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("update users set name=?,password=?,email=? where id=?");
			ps.setString(1,u.getFirstName());
			ps.setString(2,u.getLastName());
			ps.setString(3,u.getUsername());
			ps.setString(4,u.getPassword());
			ps.setString(5,u.getEmail());
			ps.setInt(6,u.getId());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int delete(UserBean u){
		int status=0;
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from users where id=?");
			ps.setInt(1,u.getId());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}
	public static List<UserBean> getAllRecords(){
		List<UserBean> list=new ArrayList<UserBean>();
		
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from users");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				UserBean u=new UserBean();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static UserBean getRecordById(int id){
		UserBean u=null;
		try{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from users where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u=new UserBean();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setEmail(rs.getString("email"));
			}
		}catch(Exception e){System.out.println(e);}
		return u;
	}
   }