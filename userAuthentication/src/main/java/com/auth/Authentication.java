package com.auth;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/auth")
public class Authentication extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URL = "jdbc:mysql://localhost/jdbcdb";
	    final String USER = "root";
	    final String PASSWORD = "1234";
		String name = (String) request.getParameter("lname");
		String pass = (String) request.getParameter("lpass");
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement s =  connection.createStatement();
		String query = "select * from auth where nam='"+name+"' and pass='"+pass+"';";
		ResultSet rs = s.executeQuery(query);
		rs.next();
		response.getWriter().println(" These are your credentials    ------>   name : "+rs.getString("nam")+  ",  password : " + rs.getString("pass"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print(e);
			if(e.toString().equals("java.sql.SQLException: Illegal operation on empty result set."))
				response.getWriter().print(" no such user please try to register at index.html");
		}
	}

}
