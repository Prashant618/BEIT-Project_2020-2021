package userPackages;

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
import dao.Book;
import dao.Countries;

/**
 * Servlet implementation class Viewpass
 */
@WebServlet("/Viewpass")
public class Viewpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewpass() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		String username=request.getParameter("username");
		
		ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(username);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
		  JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
		
		
	}
	public  ArrayList<Book> getAllCountries( String username ) {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();      
	        
	        // String sql="select * from user where username='"+username+"' and password='"+password+"'";
	        
	        String sql = "select * from montlypass where username='"+username+"'";
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	    	     
	            while(rs.next()) { 

		    	Book book = new Book();
		    	
		    	book.setUsername(rs.getString("username"));
		    	book.setBusno(rs.getString("busno"));
		    	book.setBusplate(rs.getString("busplate"));
		    	book.setSource(rs.getString("source"));
		    	book.setDestination(rs.getString("Destination"));
		    	book.setFrom(rs.getString("from"));
		    	book.setTo(rs.getString("to"));
		    	
		    	
	            booklist.add(book);
	           	               
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	
	}

}
