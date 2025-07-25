

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;
import com.mysql.cj.Session;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection connection;
   PreparedStatement pstmt;
	
	public void dbInit() {
		String url="jdbc:mysql://localhost:3306/flyaway";
		String  username="root";
		String password="har123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,username,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Some Error occured :"+ e);
		}
		
	}
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  PrintWriter out=response.getWriter();
	 
	  
	 try {
		  String Email=request.getParameter("email");
		  String  FisrtName=request.getParameter("fname");
		  String LastName=request.getParameter("lname");
		  Integer Age=Integer.parseInt(request.getParameter("age"));
		  String mobile=request.getParameter("mobile");
		  String gender=request.getParameter("gender");
		  String Password=request.getParameter("password");
		  String country=request.getParameter("country");
		  
		  dbInit();
	
		  String sqlQuery="insert into userdetails values(?,?,?,?,?,?,?,?)";
		  PreparedStatement pstmt=connection.prepareStatement(sqlQuery);
    	  pstmt=connection.prepareStatement(sqlQuery);
		  
		  pstmt.setString(1, Email);
		  pstmt.setString(2, FisrtName);
		  pstmt.setString(3, LastName);
		  pstmt.setInt(4, Age);
		  pstmt.setString(5, mobile);
		  pstmt.setString(6, gender);
		  pstmt.setString(7, Password);
		  pstmt.setString(8, country);
		  
		  pstmt.executeUpdate();
		  out.print("Registeration Successful");
		  HttpSession httpSession=request.getSession();
		  if(httpSession!=null) {
			  request.getAttribute("flightno");
		  }
		 Object flight=httpSession.getAttribute("flightno");
		 httpSession.setAttribute("flightno", flight);
		 response.sendRedirect("Payment.jsp");
		  
	 }catch (Exception e) {
		out.print("something went wrong please try again");
	}
	  
	
	}

}
