package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegistrationServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String email = request.getParameter("em");
		String phone = request.getParameter("ph");
		String password = request.getParameter("pwd");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_database","root","root");
			PreparedStatement pst = con.prepareStatement("insert into inform_table(name,age,mobile,email,password) values(?,?,?,?,?)");
			pst.setString(1, name);
			pst.setInt(2, Integer.parseInt(age));
			pst.setString(3,phone);
			pst.setString(4, email);
			pst.setString(5, password);
			
			pst.executeUpdate();
			con.close();
			out.println("<h1 style=color:red>Registration done successfully</h1>");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
