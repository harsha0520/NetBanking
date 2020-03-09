package com.jspiders.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class CreateProfileServlet extends HttpServlet 
{
	static String usr;
	static String pwd;
	static int accno;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter(); 
		String fName=req.getParameter("FirstName");
		String mName=req.getParameter("MiddleName");
		String lName=req.getParameter("LastName");
		String[] gender=req.getParameterValues("Gender");
		String dob=req.getParameter("DOB");
	    String email=req.getParameter("email");
		String addr1=req.getParameter("Addressline1");
		String addr2=req.getParameter("Addressline2");
	    String city=req.getParameter("City");
		String state=req.getParameter("State");
		String pin=req.getParameter("Pincode");
		
		String contact=req.getParameter("Contactno");
		 usr=req.getParameter("Username");
		 pwd=req.getParameter("Password");
		accno=Integer.parseInt(req.getParameter("accno"));
		
		
		/*	
		 out.println("FirstName:"+fName);
		out.println("MiddleName:"+mName);
		out.println("LastName:"+lName);
		out.println("Gender:"+gender);
		out.println("DOB:"+dob);
		out.println("email:"+email);
		out.println("Address1:"+addr1);
		out.println("Address2:"+addr2);
		out.println("City:"+city);
		out.println("Pincode:"+pin);
		out.println("Contactno:"+contact);
		out.println("Username:"+usr);
		out.println("Password:"+pwd); */
		
		
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			String dburl="jdbc:mysql://localhost:3306/Netbanking?user=root&password=root";
			Connection con=DriverManager.getConnection(dburl);
			String query1 = " insert into Registration "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			
			PreparedStatement pstmt1=con.prepareStatement(query1);
					
					
					
					pstmt1.setString(1, fName);
					pstmt1.setString(2,mName );
					pstmt1.setString(3,lName );
					pstmt1.setString(4,gender[0] );
					pstmt1.setString(5,dob);
					pstmt1.setString(6, email);
					pstmt1.setString(7,addr1 );
					pstmt1.setString(8,addr2 );
					pstmt1.setString(9, city);
					pstmt1.setString(10,state );
					pstmt1.setInt(11, Integer.parseInt(pin));
					pstmt1.setString(12,(contact));
					pstmt1.setString(13,usr );
					pstmt1.setInt(14,accno);
					
					
					
					String query2 = " insert into login "
							+ " values(?,?) ";
					
					
					PreparedStatement 	pstmt2=con.prepareStatement(query2);
					
					pstmt2.setString(1,usr );
					pstmt2.setString(2, pwd);
					
					int res1 = pstmt1.executeUpdate();
					int res2 = pstmt2.executeUpdate();
				
					if(res1!=0 && res2!=0)
					{
						System.out.println("profile updated...");
						out.println("WELCOME! YOUR ACCOUNT HAS OPENED");
						RequestDispatcher rd=req.getRequestDispatcher("signin.html");
						rd.forward(req, resp);
					}
					else
					{
						out.print("Sorry, Registration Failed. Please try again ");
						RequestDispatcher rd=req.getRequestDispatcher("newaccount.html");
						rd.forward(req, resp);
					}
			
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		out.close();
		
			
			
			
		}	

	}
		
	

	
	
	
	

