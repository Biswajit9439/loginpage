package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("em");
		String password = request.getParameter("pwd");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_database","root","root");
			PreparedStatement pst = con.prepareStatement("select * from inform_table where email=? and password=?");
			
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				out.println("Welcome to the home page");
				out.println("Your name is: "+rs.getString("name"));
				out.println("your age is:"+rs.getString("age"));
				out.println("youe email id is:"+rs.getString("email"));
			}
			else
			{
				out.println("invalid email id or password please check again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
