package com.jsp.servlet.mobiledb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UpdateMobileServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res ) {
		int mobileId = Integer.parseInt(req.getParameter("mobileId"));
		String mobileModel = req.getParameter("mobileModel");
		String mobileBrand =  req.getParameter("mobileBrand");	
		
		//JDBC
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_mobiledb?user=root&password=root");
			
			PreparedStatement preparedStatement = connection.prepareStatement("Update mobile SET mobileModel =  ?,mobileBrand = ? where mobileId = ? ");
			preparedStatement.setString(1,mobileModel);
			preparedStatement.setString(2, mobileBrand);
			preparedStatement.setInt(3,mobileId);
			
			int rowsInserted = preparedStatement.executeUpdate();
			PrintWriter pw = res.getWriter();
			if (rowsInserted > 0) {
				pw.print("<h1>"+ rowsInserted + "data Update !!</h1>");
				
			}else {
				pw.print("<h1> Data Not Updated </h1>");
			}
			
			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}