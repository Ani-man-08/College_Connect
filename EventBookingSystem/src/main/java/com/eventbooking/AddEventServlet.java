package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.eventbooking.util.DBUtil;

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String category = req.getParameter("category");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String venue = req.getParameter("venue");
        String capacity = req.getParameter("capacity");

        // ✅ Get department as integer directly from form
        String deptStr = req.getParameter("department"); 
        int departmentId = Integer.parseInt(deptStr);

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO events (title, category, event_date, event_time, venue, capacity, department_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, category);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setTime(4, java.sql.Time.valueOf(time + ":00"));
            ps.setString(5, venue);
            ps.setInt(6, Integer.parseInt(capacity));
            ps.setInt(7, departmentId); // directly inserted

            ps.executeUpdate();
            ps.close();

            resp.sendRedirect("admindashboard");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error adding event: " + e.getMessage(), e);
        }
    }
}
