package com.jsp.servlet.mobiledb;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DeleteMobileServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res ) {
		int mobileId = Integer.parseInt(req.getParameter("mobileId"));
		
		//JDBC
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_mobiledb?user=root&password=root");
			
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE from mobile where mobileId = ?");
			preparedStatement.setInt(1,mobileId);
			
			int rowsInserted = preparedStatement.executeUpdate();
			PrintWriter pw = res.getWriter();
			if (rowsInserted > 0) {
				pw.print("<h1>"+ rowsInserted + "data deleted !!</h1>");
				
			}else {
				pw.print("<h1> Data Not Deleted </h1>");
			}
			
			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
