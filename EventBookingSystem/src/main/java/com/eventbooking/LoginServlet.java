package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.eventbooking.util.DBUtil;

@WebServlet("/LoginServlet")  // <-- replaces web.xml mapping
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String role = rs.getString("role");

                // Debug print
                System.out.println("DEBUG: User=" + username + " Role=" + role);

                HttpSession session = req.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Redirect based on role
                if ("ADMIN".equalsIgnoreCase(role)) {
                    resp.sendRedirect("admindashboard");
                } else if ("DEPARTMENT".equalsIgnoreCase(role)) {
                    resp.sendRedirect("addevent.jsp");
                } else { // STUDENT
                    resp.sendRedirect("events");
                }

            } else {
                out.println("<h3 style='color:red;'>Invalid username or password!</h3>");
                out.println("<a href='login.jsp'>Try Again</a>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
