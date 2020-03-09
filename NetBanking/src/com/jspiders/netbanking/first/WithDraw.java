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

public class WithDraw extends HttpServlet {
	int balance;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		int accno = Integer.parseInt(req.getParameter("accno"));
		int Amount = Integer.parseInt(req.getParameter("amount"));

		if(CreateProfileServlet.usr.equals(username) && CreateProfileServlet.accno==(accno))
		{
			try {
				Driver ref=new Driver();
				DriverManager.registerDriver(ref);
				
				String dburl="jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
				Connection con=DriverManager.getConnection(dburl);
				
				String query="SELECT balance FROM AccountDetails WHERE username="+username+" AND accno="+accno+"";
				Statement stmt= con.createStatement();
				ResultSet res=stmt.executeQuery(query);
				while(res.next())
				{
					 balance = res.getInt("balance");
				}
				if (balance>Amount)
				{
					balance=balance-Amount;
					String query1="UPDATE AccountDetails SET WHERE  username="+username+" AND accno="+accno+"";
					PreparedStatement pstmt1=con.prepareStatement(query1);
					pstmt1.setInt(3,balance);
					
					int res1=pstmt1.executeUpdate();
					if (res1>0)
					{
						RequestDispatcher rd=req.getRequestDispatcher("CheckBal.html");
						rd.forward(req,resp);
					}
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			out.println("Sorry! please enter the proper credentials");
			RequestDispatcher rd=req.getRequestDispatcher("withdraw1.html");
			rd.forward(req, resp);
		}
		
	}

} 
