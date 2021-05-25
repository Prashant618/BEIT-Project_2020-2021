package Mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
 * Servlet implementation class checklogin
 */
@WebServlet("/checklogin")
public class checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 DatabaseConnection dbcon;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checklogin() {
        super();
        dbcon=new DatabaseConnection();
        dbcon.dbconnection();
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
		String userName=request.getParameter("name");
		String MobileNumber=request.getParameter("pass");
		
		PrintWriter out=response.getWriter();
		 ArrayList<Book> books=new ArrayList<Book>();
		 
		  try {
			books=getAllCountries(userName,MobileNumber);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
		  
	}
	public  ArrayList<Book> getAllCountries(String userName, String MobileNumber) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        
	       // String pswd=StrongAES.Encrypting(MobileNumber);
	        
	        String sql="select * from userentry where username='"+userName+"' and encpassword='"+MobileNumber+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=dbcon.getResultSet(sql);
	            if(rs.next()) { 
	            	
	            	String mobilenum=rs.getString("mobile");
	            	Book book=new Book();
	            book.setBookName("CORRECT");
	            Random random = new Random();
	            String id = String.format("%04d", random.nextInt(10000));
	            String query="update  userentry set otp=? where userName='"+userName+"'";
	            
	            PreparedStatement ps=dbcon.dbconnection().prepareStatement(query);
	            ps.setString(1, id);     
	         ps.executeUpdate();
	                booklist.add(book);
	            //  SendSms.sendmessageto91(mobilenum,"Your OTP is"+id,"EVOTIN");
	            }
	            else
	            {
	              	Book book=new Book();
		            book.setBookName("INCORRECT");
		           
		                booklist.add(book);
	            	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	}
}
