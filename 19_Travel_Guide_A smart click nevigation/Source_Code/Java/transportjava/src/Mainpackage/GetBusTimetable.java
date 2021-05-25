package Mainpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class GetBusTimetable
 */
@WebServlet("/GetBusTimetable")
public class GetBusTimetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	
	 Connection con;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBusTimetable() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		 String sql1="select * from businfo where source='"+source+"' and destination='"+destination+"'";
			
	    	PreparedStatement ps11;
	    	ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
	    	try {
				ps11 = con.prepareStatement(sql1);
				  ResultSet rs1=ps11.executeQuery();
				    
		            while(rs1.next()) { 
		            	studentinfo studentdata=new studentinfo();
		            	String uptiming=rs1.getString("UpTiming");
		            	String downtiming=rs1.getString("DownTiming");
		            	
		            	
//		     		            	studentdata.setLati(rs1.getString("latitude"));
//		     		            	studentdata.setLongi(rs1.getString("longitude"));
//		     		            	studentdata.setSource(rs12.getString("Source"));
//		     		            	studentdata.setDestination(rs12.getString("Destination"));
//		     		            	
//		     		            	studentdata.setTimeslot(rs1.getString("time"));
		     		            	studentdata.setBusnumber(rs1.getString("busnumber"));
		     		            	studentdata.setBusplatenumber(rs1.getString("BusplateNumber"));
//		     		            	
		     		            	studentdata.setStops(uptiming);
		     		            	studentdata.setStudentname(downtiming);
		     		            	studentdata.setStops1(rs1.getString("stops"));
		     		            	studentList.add(studentdata);
		     		            	
		     		            	
		            	
		     		           
		            	
			
		            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	
	    	
	    	  Gson gson = new Gson();
			  JsonElement element = gson.toJsonTree(studentList, new TypeToken<List<Countries>>() {}.getType());
	JsonObject jsonObject=new JsonObject();

			  JsonArray jsonArray = element.getAsJsonArray();
			  jsonObject.add("jsonarrayval", jsonArray);
			  response.setContentType("application/json");
			  response.getWriter().print(jsonObject);
	
			 
		
	
	}

}
