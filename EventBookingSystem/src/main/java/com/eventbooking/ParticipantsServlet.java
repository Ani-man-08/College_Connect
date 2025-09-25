package com.eventbooking;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.eventbooking.util.DBUtil;

@WebServlet("/ParticipantsServlet")
public class ParticipantsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equalsIgnoreCase("admin")) {
            response.sendRedirect("login.jsp");
            return;
        }

        int eventId = Integer.parseInt(request.getParameter("eventId"));
        List<Map<String, Object>> participants = new ArrayList<>();
        String eventTitle = "";

        try (Connection con = DBUtil.getConnection()) {
            // get event title
            PreparedStatement psEvent = con.prepareStatement("SELECT title FROM events WHERE event_id=?");
            psEvent.setInt(1, eventId);
            ResultSet rsEvent = psEvent.executeQuery();
            if (rsEvent.next()) {
                eventTitle = rsEvent.getString("title");
            }
            rsEvent.close();
            psEvent.close();

            // get participants (NO email_id here)
            PreparedStatement ps = con.prepareStatement(
                "SELECT u.username, u.department, r.registered_at " +
                "FROM users u JOIN registrations r ON u.user_id = r.user_id " +
                "WHERE r.event_id = ?"
            );
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("username", rs.getString("username"));
                row.put("department", rs.getString("department"));
                row.put("registered_at", rs.getTimestamp("registered_at"));
                participants.add(row);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching participants: " + e.getMessage());
            return;
        }

        request.setAttribute("participants", participants);
        request.setAttribute("eventId", eventId);
        request.setAttribute("eventTitle", eventTitle);

        RequestDispatcher rd = request.getRequestDispatcher("participants.jsp");
        rd.forward(request, response);
    }
}
