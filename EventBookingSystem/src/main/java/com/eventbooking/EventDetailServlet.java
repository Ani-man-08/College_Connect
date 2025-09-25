package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.eventbooking.util.DBUtil;

@WebServlet("/eventDetail")  // <-- replaces web.xml mapping
public class EventDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Upcoming Events</title></head><body>");
        out.println("<h2>Upcoming Events</h2>");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM events ORDER BY event_date ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>Title</th><th>Category</th><th>Date</th><th>Venue</th><th>Description</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("category") + "</td>");
                out.println("<td>" + rs.getDate("event_date") + "</td>");
                out.println("<td>" + rs.getString("venue") + "</td>");  // ✅ changed "location" → "venue"
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error fetching events: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
