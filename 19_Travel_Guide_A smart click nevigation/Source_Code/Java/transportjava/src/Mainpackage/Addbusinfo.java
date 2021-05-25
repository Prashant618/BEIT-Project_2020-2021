package Mainpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;
import dao.Book;
import dao.Countries;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Addbusinfo
 */
@WebServlet("/Addbusinfo")
public class Addbusinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();	
	 Connection con;  
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addbusinfo() {
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
		
		
		
		String busnumber=request.getParameter("busno");
		String busplatenumber=request.getParameter("busplate");
		String source=request.getParameter("sourc");
		String destination=request.getParameter("destinatio");
		String stop1=request.getParameter("stop");
		String startingtime=request.getParameter("uptime");
		String DownTiming=request.getParameter("downtime");

		
		 ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(busnumber,busplatenumber,source,destination,stop1,startingtime,DownTiming);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String busnumber, String busplatenumber, String source, String destination,String stop1,String startingtime, String DownTiming ) {
	     //connection = con
		
		
		String qry="insert into businfo(busnumber,BusplateNumber,Source,Destination,UpTiming,stops,DownTiming) values('"+busnumber+"','"+
		busplatenumber+"','"+source+"','"+destination+"','"+startingtime+"','"+stop1+"','"+DownTiming+"')";
		System.out.println();
		int i=db1.getUpdate(qry);

		
		
	        ArrayList<Book> booklist = new ArrayList<Book>();
	       
	    	
	    	     Book book=new Book();
	            if(i>0) { 
	            	
	            	
	            	
	            book.setBookName("1");
	           
	               
	               
	            }
	            else
	            {
	            	
	            	 book.setBookName("0");
	            }
	            booklist.add(book);
	         

	        return booklist;
	
	}

}
