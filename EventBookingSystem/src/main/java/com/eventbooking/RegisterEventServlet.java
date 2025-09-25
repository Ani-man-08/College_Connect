package com.eventbooking;

import com.eventbooking.util.DBUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/RegisterEventServlet")
public class RegisterEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        String eventIdStr = request.getParameter("eventId");
        if (eventIdStr == null) {
            response.sendRedirect("events");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int eventId = Integer.parseInt(eventIdStr);

        String message;

        try (Connection con = DBUtil.getConnection()) {

        
            PreparedStatement checkEvent = con.prepareStatement(
                    "SELECT event_id FROM events WHERE event_id = ?");
            checkEvent.setInt(1, eventId);
            ResultSet rsEvent = checkEvent.executeQuery();

            if (!rsEvent.next()) {
                message = "❌ Registration failed: This event does not exist.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("registerevent.jsp").forward(request, response);
                return;
            }

            rsEvent.close();
            checkEvent.close();

            PreparedStatement check = con.prepareStatement(
                    "SELECT * FROM registrations WHERE user_id=? AND event_id=?");
            check.setInt(1, userId);
            check.setInt(2, eventId);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                message = "⚠️ You are already registered for this event.";
            } else {
                
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO registrations (user_id, event_id, registration_date) VALUES (?, ?, NOW())");
                ps.setInt(1, userId);
                ps.setInt(2, eventId);
                ps.executeUpdate();
                ps.close();

                message = "✅ Successfully registered for the event!";
            }

            rs.close();
            check.close();

        } catch (Exception e) {
       
            message = "❌ Registration failed: " + e.getMessage();
        }


        request.setAttribute("message", message);
        request.getRequestDispatcher("registerevent.jsp").forward(request, response);
    }
}
