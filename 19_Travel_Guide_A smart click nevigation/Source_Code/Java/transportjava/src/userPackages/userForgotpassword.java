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
 * Servlet implementation class userForgotpassword
 */
@WebServlet("/userForgotpassword")
public class userForgotpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userForgotpassword() {
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
		String passward=request.getParameter("passward");
		String cpassward=request.getParameter("cpassward");
		
		
		 ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(username,passward,cpassward);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String username, String passward, String cpassward) {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        String sql="select * from userregistertable where username='"+username+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	    	     Book book=new Book();
	             if(rs.next()) { 
	            	
	            	   String query="UPDATE userregistertable SET passward='"+passward+"' WHERE username='"+username+"'";
	            		System.out.println();
	            		int i=db1.getUpdate(query);

	            		
	            		
	            	        ArrayList<Book> booklist1 = new ArrayList<Book>();
	            	       
	            	    	
	            	    	     Book book1=new Book();
	            	            if(i>0) { 
	            	            	
	            	            	
	            	            	
	            	            book1.setBookName("1");
	            	           
	            	               
	            	               
	            	            }
	            	            else
	            	            {
	            	            	
	            	            	 book1.setBookName("0");
	            	            }
	            	            booklist1.add(book1);
	            	         

	            	        return booklist1;
	            
	            }
	            else
	            {
	            	 ArrayList<Book> booklist1 = new ArrayList<Book>();
          	       
         	    	int i=0;
    	    	     Book book1=new Book();
    	            if(i>0) { 
    	            	
    	            	
    	            	
    	            book1.setBookName("1");
    	           
    	               
    	               
    	            }
    	            else
    	            {
    	            	
    	            	 book1.setBookName("0");
    	            }
    	            booklist1.add(book1);
    	         

    	        return booklist1;
	            	    
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;	
	
	}
}
