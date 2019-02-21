import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("name");
			String password = request.getParameter("pass");	
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			boolean flag = false;
			try
			{
				Class.forName("org.mariadb.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Login", "root", "root");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from user");
				while(rs.next())
				{
					if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
					{
						flag = true;
						response.sendRedirect("success.html");
					}
				}
				if(flag==false)
				{
					
					out.print("<h1>Invalid User!</h1>");
					
				}
				
			} catch(Exception ex)
			{
				out.print(ex);
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
