package com.eventbooking;

import com.eventbooking.util.DBUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/myregistrations")
public class MyRegistrationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<Map<String, Object>> registrations = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT r.registration_id, e.title, e.event_date, e.venue, r.registration_date " +
                         "FROM registrations r " +
                         "JOIN events e ON r.event_id = e.event_id " +
                         "WHERE r.user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> reg = new HashMap<>();
                reg.put("title", rs.getString("title"));
                reg.put("event_date", rs.getDate("event_date"));
                reg.put("venue", rs.getString("venue"));
                reg.put("registration_date", rs.getDate("registration_date"));
                registrations.add(reg);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        
        req.setAttribute("registrations", registrations);
        req.getRequestDispatcher("myregistrations.jsp").forward(req, resp);
    }
}
