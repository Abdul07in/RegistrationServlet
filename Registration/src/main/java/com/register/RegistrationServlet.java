package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		PreparedStatement pst = null;
		Connection con = null;
		String name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		String email = request.getParameter("user_email");
	
		try {
			Thread.sleep(1500);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","root");
			String sql = "insert into users(name,password,email) values(?,?,?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setString(3,email);
			
			if (pst.executeUpdate() > 0) {
				out.println("Success");
			}else
			{
				out.println("Error");
			}
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			out.println("Error");
		}finally {
			try {
				pst.close();
				con.close();
				out.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}

}
