package com.eventbooking;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.eventbooking.util.DBUtil;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Map<String, Object>> events = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM events ORDER BY event_date ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> e = new HashMap<>();
                e.put("event_id", rs.getInt("event_id"));
                e.put("title", rs.getString("title"));
                e.put("category", rs.getString("category"));
                e.put("event_date", rs.getDate("event_date"));
                e.put("event_time", rs.getTime("event_time"));
                e.put("venue", rs.getString("venue"));
                e.put("capacity", rs.getInt("capacity"));
                e.put("description", rs.getString("description"));
                events.add(e);
            }
        } catch (Exception e) {
            throw new ServletException("Error fetching events", e);
        }

   
        req.setAttribute("events", events);
        RequestDispatcher rd = req.getRequestDispatcher("events.jsp");
        rd.forward(req, resp);
    }
}
