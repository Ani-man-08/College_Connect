package com.eventbooking;

import com.eventbooking.util.DBUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String eventIdStr = request.getParameter("eventId");
        
        if (eventIdStr != null) {
            int eventId = Integer.parseInt(eventIdStr);
            try (Connection con = DBUtil.getConnection()) {
                
                PreparedStatement ps1 = con.prepareStatement("DELETE FROM registrations WHERE event_id = ?");
                ps1.setInt(1, eventId);
                ps1.executeUpdate();
                ps1.close();

                
                PreparedStatement ps2 = con.prepareStatement("DELETE FROM events WHERE event_id = ?");
                ps2.setInt(1, eventId);
                ps2.executeUpdate();
                ps2.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
        
       
        response.sendRedirect("admindashboard");
    }
}
