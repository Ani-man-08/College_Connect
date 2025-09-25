package com.eventbooking;

import com.eventbooking.util.DBUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/admindashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("role") == null ||
                !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Map<String, Object>> events = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT event_id, title, event_date, venue, capacity FROM events ORDER BY event_date ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> event = new HashMap<>();
                event.put("event_id", rs.getInt("event_id"));
                event.put("title", rs.getString("title"));
                event.put("event_date", rs.getDate("event_date"));
                event.put("venue", rs.getString("venue"));
                event.put("capacity", rs.getInt("capacity"));
                events.add(event);
            }

        } catch (SQLException e) {
            throw new ServletException("Database error while fetching events", e);
        }

        // ✅ Send events to JSP
        req.setAttribute("events", events);
        req.getRequestDispatcher("admindashboard.jsp").forward(req, resp);
    }
}
