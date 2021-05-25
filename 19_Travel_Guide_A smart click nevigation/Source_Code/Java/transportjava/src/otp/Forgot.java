package otp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgot() {
        super();
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
		// TODO Auto-generated method stub
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		
			try{
				String query = "SELECT * FROM users where username='"+username+"'";
				ResultSet rs = db.getResultSet(query);
				if (rs.next()) {
					String name = rs.getString("fullname");
					String email = rs.getString("email");
					String msg = "";
					msg = name+" Your password is " + rs.getString("password");
					
					new SimpleSendEmail(email, "Password Recovery", msg);
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Password send to your Email address');");
					out.println("location=\"index.jsp\"");
					out.println("</script>");
				}
				else{
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Username does not found');");
					out.println("location=\"forgot.jsp\"");
					out.println("</script>");
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
}
