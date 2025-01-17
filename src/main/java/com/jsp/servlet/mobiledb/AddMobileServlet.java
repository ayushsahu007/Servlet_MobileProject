package com.jsp.servlet.mobiledb;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddMobileServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mobileId = Integer.parseInt(req.getParameter("mobileId"));
		String mobileModel = req.getParameter("mobileModel");
		String mobileBrand =  req.getParameter("mobileBrand");	
		int mobilePrice = Integer.parseInt(req.getParameter("mobilePrice"));
		
		
		//JDBC
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servlet_mobiledb?user=root&password=root");			
		
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mobile VALUES(?,?,?,?)");
			preparedStatement.setInt(1,mobileId);
			preparedStatement.setString(2,mobileModel);
			preparedStatement.setString(3, mobileBrand);
			preparedStatement.setInt(4, mobilePrice);
			
			int rowsInserted = preparedStatement.executeUpdate();
			PrintWriter pw = res.getWriter();
			if (rowsInserted > 0) {
				pw.print("<h1>"+ rowsInserted + "data inserted !!</h1>");
				
			}else {
				pw.print("<h1> Data Not Insertes </h1>");
			}
			
					} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		         
	}
	
	
	
}