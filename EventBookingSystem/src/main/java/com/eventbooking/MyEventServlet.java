package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.eventbooking.util.DBUtil;

@WebServlet("/myEvents") 
public class MyEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        out.println("<html><head><title>My Events</title></head><body>");
        out.println("<h2>My Registered Events</h2>");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT e.title, e.category, e.event_date, e.event_time, e.venue, e.description " +
                         "FROM events e JOIN registrations r ON e.event_id = r.event_id " +
                         "WHERE r.user_id = ? ORDER BY e.event_date ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>Title</th><th>Category</th><th>Date</th><th>Time</th><th>Venue</th><th>Description</th></tr>");

            boolean hasEvents = false;
            while (rs.next()) {
                hasEvents = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("category") + "</td>");
                out.println("<td>" + rs.getDate("event_date") + "</td>");
                out.println("<td>" + rs.getTime("event_time") + "</td>");
                out.println("<td>" + rs.getString("venue") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            if (!hasEvents) {
                out.println("<p>You have not registered for any events yet.</p>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("<br/><a href='events'>Back to Events</a>");
        out.println("</body></html>");
    }
}
