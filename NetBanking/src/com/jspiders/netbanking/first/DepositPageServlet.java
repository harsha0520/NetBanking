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

/*public class DepositPageServlet extends HttpServlet {
	int balance;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter(); 
		String usr=req.getParameter("Username");
		String amt=req.getParameter("Amount");
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			String dburl="jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
			Connection con=DriverManager.getConnection(dburl);
			String query1 = " insert into deposit "
					+ " values(?,?) ";
			PreparedStatement pstmt=con.prepareStatement(query1);
			pstmt.setString(1,usr );
			pstmt.setDouble(2, Double.parseDouble(amt));
			
		    int res = pstmt.executeUpdate();
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	 
	
	

}*/


public class DepositPageServlet extends HttpServlet {
	
	int balance=500;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		int accno=Integer.parseInt(req.getParameter("accno"));
		int  Amount= Integer.parseInt(req.getParameter("amount"));
		
		
			try {
				Driver ref=new Driver();
				DriverManager.registerDriver(ref);
				
				String dburl="jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
				Connection con=DriverManager.getConnection(dburl);
				
				String query="SELECT * FROM Netbanking";
				Statement stmt=con.createStatement();
				ResultSet res=stmt.executeQuery(query);
				
				
				
				if (res.next())
				{
					String uname=res.getString("username");
					
						
					if (uname.equals(username))
					{
				String query1="INSERT INTO AccountDetails values(?,?)";
				PreparedStatement pstmt=con.prepareStatement(query1);
				pstmt.setString(1,username);
				pstmt.setInt(2,accno);
				
					balance=balance+Amount;
					String query2="UPDATE AccountDetails SET WHERE  username="+username+" AND accno="+accno+"";
					PreparedStatement pstmt1=con.prepareStatement(query2);
					pstmt1.setInt(3,balance);
				
					int res2=pstmt1.executeUpdate();
					if (res2>0)
					{
						RequestDispatcher rd=req.getRequestDispatcher("CheckBal.html");
						rd.forward(req,resp);
					}
			     }
				}
				else
				{
					out.println("Please Enter Proper Credentials");
					RequestDispatcher rd=req.getRequestDispatcher("Deposit.html");
					rd.forward(req,resp);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}

