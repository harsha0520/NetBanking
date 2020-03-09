package com.jspiders.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class LoginPageServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter(); 
		String usr=req.getParameter("Username");
		String pwd=req.getParameter("Password");
		
		Connection con=null;
		PreparedStatement pstmt1=null;
		ResultSet res=null;
		 try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			String dburl="jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
			con=DriverManager.getConnection(dburl);
			String query1= " Select Username,Password " + 
			               " from login " +
					       " where Username = ? ";
			 pstmt1=con.prepareStatement(query1);
			 pstmt1.setString(1, usr);
			
			 res = pstmt1.executeQuery();
			
			
	while(res.next()) 
			
			{
				String usr1=res.getString("Username");
				String pwd1=res.getString("Password");
				
				if(usr.equals(usr1) && pwd.equals(pwd1)) {
					out.println("Successfully Logged In");
					RequestDispatcher rd=req.getRequestDispatcher("welcome.html");
					rd.forward(req, resp);
					
				}
				
				else {
					out.println("Invalid password");
					RequestDispatcher rd=req.getRequestDispatcher("signin.html");
					rd.forward(req, resp);
				}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
			if(con!=null)
			{
		       con.close();
			}
			if(pstmt1!=null)
			{
				pstmt1.close();
			}
			
			if(res!=null)
			{
				res.close();
			}
			
		}
			
			catch (SQLException e) {
					
					e.printStackTrace();
				}
		}

}
}
