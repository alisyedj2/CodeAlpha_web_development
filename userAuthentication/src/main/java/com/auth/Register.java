package com.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg")

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String URL = "jdbc:mysql://localhost/jdbcdb";
	    final String USER = "root";
	    final String PASSWORD = "1234";
		String name = (String) request.getParameter("name");
		String pass = (String) request.getParameter("pass");
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement s =  connection.createStatement();
		String query = "select * from auth where nam ='" + name + "' and pass = '" +pass +"';";
		if(s.executeQuery(query).next())
			response.getWriter().print("user exists try to login in login.html");
		else {
		    query = "insert into auth(nam,pass) values('"+ name+ "', '"+pass+"');";
		    s.execute(query);
			response.sendRedirect("http://localhost:8081/userAuthentication/login.html");
		    }
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
