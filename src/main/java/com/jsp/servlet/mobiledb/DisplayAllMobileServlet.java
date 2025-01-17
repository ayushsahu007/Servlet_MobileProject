package com.jsp.servlet.mobiledb;

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

public class DisplayAllMobileServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//JDBC 
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_mobiledb?user=root&password=root");
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mobile");
		ResultSet rs = preparedStatement.executeQuery();
		
		PrintWriter pw = res.getWriter();
		pw.print("MobileId\tMobileName\tMobileBrand\tMobilePrice");
	   while(rs.next()) {
		   pw.print(rs.getInt(1)+"\t\t");
		   pw.print(rs.getString(2)+"\t\t");
		   pw.print(rs.getString(3)+"\t\t");
		   pw.print(rs.getInt(4)+"\t\t");
		   pw.println();
	   }
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
