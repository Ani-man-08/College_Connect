package com.eventbooking;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.eventbooking.util.DBUtil;

@WebServlet("/EditEventServlet")
public class EditEventServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int eventId = Integer.parseInt(req.getParameter("eventId"));

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM events WHERE event_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("eventId", rs.getInt("event_id"));
                req.setAttribute("title", rs.getString("title"));
                req.setAttribute("category", rs.getString("category"));
                req.setAttribute("date", rs.getDate("event_date").toString());
                req.setAttribute("time", rs.getTime("event_time").toString());
                req.setAttribute("venue", rs.getString("venue"));
                req.setAttribute("capacity", rs.getInt("capacity"));
                req.setAttribute("departmentId", rs.getInt("department_id"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error loading event: " + e.getMessage());
        }

       
        RequestDispatcher rd = req.getRequestDispatcher("editevent.jsp");
        rd.forward(req, resp);
    }
}
