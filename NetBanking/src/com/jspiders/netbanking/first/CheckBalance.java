package com.jspiders.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class CheckBalance extends HttpServlet {
	int balance;
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	PrintWriter out=resp.getWriter();
	
	String username=req.getParameter("username");
	int accno = Integer.parseInt(req.getParameter("accno"));
	String [] str =req.getParameterValues("r1");
	
	
	{
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			
			String dburl= "jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
			Connection con = DriverManager.getConnection(dburl);
			
			String query="SELECT * FROM AccountDetails";
			Statement stmt=con.createStatement();
			ResultSet res=stmt.executeQuery(query);
			if(res.next())
			{
				String uname=res.getString("username");
				  balance = res.getInt(balance);
				  System.out.println(balance);
				  if (username.equals(uname))
				  {
				 out.println("The Current balance in your account is =" + balance);
			}
			else
			{
				out.println("Sorry! please enter the proper credentials");
				RequestDispatcher rd=req.getRequestDispatcher("checkbal.html");
				rd.forward(req, resp);
			}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  }
} 
