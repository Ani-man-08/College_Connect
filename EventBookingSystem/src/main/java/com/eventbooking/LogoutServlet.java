package com.eventbooking;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();  // destroy session
        response.sendRedirect("login.jsp"); // back to login
    }
}
