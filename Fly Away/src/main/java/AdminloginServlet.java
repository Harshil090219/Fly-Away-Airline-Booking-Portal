
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

@WebServlet("/AdminloginServlet")
public class AdminloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminloginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String url = "jdbc:mysql://localhost:3306/flyaway";
		String dbusername = "root";
		String dbpassword = "har123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);

			String sqlQuery = "select * from Admindetails where email_id=? and password=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);

			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				session.setAttribute("email", rs.getString("email_id"));
				response.sendRedirect("AdminHome.jsp");
				
			}else {
				response.sendRedirect("adminlogin.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
