package busPackages;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class busRegister
 */
@WebServlet("/busRegister")
public class busRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busRegister() {
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
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		
		
		 ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(username,passward,email,phone,address);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String username, String passward, String email, String phone, String address ) {
	     //connection = con;
		
		
		String query = "insert into busregistertable values('" + username + "','" + passward
				+ "','" + email + "','" + phone + "','" + address + "')";
		System.out.println();
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

}
