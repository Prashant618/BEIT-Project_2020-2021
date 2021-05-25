package Mainpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import Database.DatabaseConnection;
import dao.Countries;
import dao.studentinfo;

/**
 * Servlet implementation class updateloc
 */
@WebServlet("/updateloc")
public class updateloc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;  
	 String source,destination,vehicle,longitude,latitude,location,name;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateloc() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 name=request.getParameter("username");
		 source=request.getParameter("source");
		 destination=request.getParameter("destination");
		 latitude=request.getParameter("lati");
		 longitude=request.getParameter("longi");
		 location=request.getParameter("location");
		 vehicle=request.getParameter("vehicle");
		
		
			
		 String sql1="select * from locationupdater where name='"+name+"'";
		
	    	PreparedStatement ps11;
	    	  int noteid = 0;
	    		try{
	  		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	  	      Date date = new Date();
	  	      String currdate=dateFormat.format(date);
	  								ps11 = con.prepareStatement(sql1);
					
					   ResultSet rs1=ps11.executeQuery();
			    
			            if(rs1.next()) { 
			            	
			            	 Calendar cal = Calendar.getInstance();
		  	    	         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		  	    	     
			              String s="update locationupdater set latitude=?,longitude=?,time=? where name=?";
			              PreparedStatement p;
			      	   	
				   	
								p = con.prepareStatement(s);
								 p.setString(1,latitude);
								 p.setString(2,longitude);
					   		        p.setString(3,sdf.format(cal.getTime()) );
					   		     p.setString(4,name );
//				   		        psmt.setString(5,note);
//				   		  psmt.setString(6,tags);
				   		
					
				   		     
					   		      
					   		      
					                   int i = p.executeUpdate();
			            }
			        	
			            else{
			            	getAllCountries(name);
			            }
			               
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
//		try {
//			studentList=getAllCountries(name);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
				  Gson gson = new Gson();
				  JsonElement element = gson.toJsonTree(studentList, new TypeToken<List<Countries>>() {}.getType());
		JsonObject jsonObject=new JsonObject();

				  JsonArray jsonArray = element.getAsJsonArray();
				  jsonObject.add("jsonarrayval", jsonArray);
				  response.setContentType("application/json");
				  response.getWriter().print(jsonObject);
		
		

		
			}
		
		
	
	


	public  ArrayList<studentinfo> getAllCountries(String name) throws Exception {
	     //connection = con;
		
	
		ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	      Date date = new Date();
	      String currdate=dateFormat.format(date);
		
	        
	    	 
	    	      
	            	
	    	    	 
	    	    	 Calendar cal = Calendar.getInstance();
	    	         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	         System.out.println( sdf.format(cal.getTime()) );
				      String currdate1=dateFormat.format(date);
	            	  
	    	    	 String query="insert into locationupdater(name,date,longitude,latitude,location,time) values(?,?,?,?,?,?)";

	    	 		
	    	 		
	    	         PreparedStatement psmt;
	    	 	
	    	 			try {
	    	 				psmt = con.prepareStatement(query);
	    	 				 psmt.setString(1,name);
	    	 				psmt.setString(2,currdate);
	    	 	   		     psmt.setString(3,longitude);
	    	 	   		  psmt.setString(4,latitude);
	    	 	   		     psmt.setString(5,location);
	    	 	   	     
	    	 	   		    		psmt.setString(6,sdf.format(cal.getTime()));
	    	 	                   int i = psmt.executeUpdate();
	    	 			} catch (SQLException e) {
	    	 				// TODO Auto-generated catch block
	    	 				e.printStackTrace();
	    	 			}
	    	 		
	    	 		
	            	
	            	 
	           
	    	     
	        return studentList;
	}

}




