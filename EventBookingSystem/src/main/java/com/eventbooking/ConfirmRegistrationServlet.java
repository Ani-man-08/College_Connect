package com.eventbooking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ConfirmRegistrationServlet")
public class ConfirmRegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String eventId = request.getParameter("eventId");
        HttpSession session = request.getSession();

        
        session.setAttribute("lastRegisteredEvent", eventId);

   
        request.setAttribute("eventId", eventId);
        request.getRequestDispatcher("confirm.jsp").forward(request, response);
    }
}
