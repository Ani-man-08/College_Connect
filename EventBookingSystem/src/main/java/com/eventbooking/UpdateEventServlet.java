package com.eventbooking;

import com.eventbooking.util.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateEventServlet")
public class UpdateEventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Get form parameters
        int eventId = Integer.parseInt(request.getParameter("event_id"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String venue = request.getParameter("venue");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String departmentId = request.getParameter("department_id");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE events SET title=?, category=?, event_date=?, event_time=?, venue=?, capacity=?, department_id=? WHERE event_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, category);
            ps.setString(3, date);
            ps.setString(4, time);
            ps.setString(5, venue);
            ps.setInt(6, capacity);
            ps.setString(7, departmentId);
            ps.setInt(8, eventId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // ✅ Redirect with success message
                request.getSession().setAttribute("message", "✅ Event updated successfully!");
            } else {
                request.getSession().setAttribute("message", "⚠️ Failed to update event.");
            }

            response.sendRedirect("admindashboard");
        } catch (Exception e) {
            throw new ServletException("Error updating event", e);
        }
    }
}
