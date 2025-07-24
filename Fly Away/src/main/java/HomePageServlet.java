

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String origin=request.getParameter("origin");
		String Destination=request.getParameter("destination");
		String date=request.getParameter("date");
		String totalPerson=request.getParameter("totalPerson");
		
		
		String url="jdbc:mysql://localhost:3306/flyaway";
		String username="root";
		String password="Rocky@4121";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			String sql="select * from flightdetails where origin=? and destination=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, Destination);
		
			ResultSet rs=pstmt.executeQuery();
			PrintWriter out=response.getWriter();
	
			
			con.close();
		//	request.setAttribute("origin", origin);
			//request.setAttribute("destination", Destination);
			FlightsEntity flightdetail=new FlightsEntity();
			request.setAttribute("origin", origin);
			request.setAttribute("destination", Destination);
			request.setAttribute("date", date);
			request.setAttribute("totalPerson", totalPerson);
			
			//request.setAttribute("destination", Destination);
			//request.getParameter("origin");
		//	request.getParameter("destination");
		//	request.getParameter("date");
		//	request.getParameter("totalPerson");
			
			
			//HttpSession session =request.getSession();
		//	session.setAttribute("flight", flightdetail);
			
		//	response.sendRedirect("FlightDetails.jsp");
		RequestDispatcher dispatcher=request.getRequestDispatcher("/FlightDetails.jsp");
		dispatcher.forward(request, response);
			
		}catch (Exception e) {
		System.out.println("Some Error occured in connection"+e);
		}
	}

}
