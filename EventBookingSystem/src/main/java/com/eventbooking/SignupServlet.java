package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.eventbooking.util.DBUtil;

@WebServlet("/signup")  // <-- This replaces web.xml mapping
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password); // TODO: hash the password in future
            ps.executeUpdate();

            out.println("<h3>Signup successful! <a href='login.jsp'>Go to Login</a></h3>");
        } catch (SQLIntegrityConstraintViolationException e) {
            out.println("<h3 style='color:red;'>Username already exists! Try again.</h3>");
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
