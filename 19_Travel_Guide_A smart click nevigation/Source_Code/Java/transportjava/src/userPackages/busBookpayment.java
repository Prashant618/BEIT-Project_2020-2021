package userPackages;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class busBookpayment
 */
@WebServlet("/busBookpayment")
public class busBookpayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busBookpayment() {
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
		String bus=request.getParameter("busnumber");
		String dest=request.getParameter("destinationofbus");
		String time=request.getParameter("timeofbus");		
		

		
		String phone="";
		try {
			phone=getphonenum(username);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(username,phone,bus,dest,time);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String username,String phone,String bus,String dest,String time) {
	     //connection = con;
		
		
		String query = "insert into bookbuspayment values('" + username + "','" + phone
				+ "','" + bus + "','" + dest + "','" + time + "')";
//		
//		String query = "INSERT INTO payment SELECT * from userregister WHERE username='"+ username +"'";
				
		int i=db1.getUpdate(query);

		
		
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
	
	private String getphonenum(String username) throws SQLException {
		String phonenum="";
		String qry="select * from userregistertable where username='"+username+"'";
		ResultSet rs=db1.getResultSet(qry);
		while(rs.next())
		{
			phonenum=rs.getString("phone");	
		}
		return phonenum;
	}

}
