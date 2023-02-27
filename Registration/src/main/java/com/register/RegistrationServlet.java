package com.register;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/register")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		PreparedStatement pst = null;
		Connection con = null;
		String name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		String email = request.getParameter("user_email");
		Part image = request.getPart("image");
		String fileName = image.getSubmittedFileName();
		
		try {
			LocalDateTime time = LocalDateTime.now();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://dbpanel.squadinfotech.in/testuser_abdul", "testuser",
					"testuser");
			String sql = "INSERT INTO `users`(`name`, `password`, `email`, `imageName`, `registerTime`) VALUES (?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, fileName);
			pst.setTimestamp(5, Timestamp.valueOf(time));

			if (pst.executeUpdate() > 0) {
				out.println("Success");
			} else {
				out.println("Error");
			}
			
			InputStream inputStream = image.getInputStream();
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);

			String path = request.getRealPath("/") + "images" + File.separator + fileName;
			
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			out.println("Error");
		} finally {
			try {
				out.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
