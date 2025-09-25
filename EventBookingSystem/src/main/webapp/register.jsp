<%@ page import="java.sql.*" %>
<%@ include file="includes/header.jsp" %>

<main class="events">
  <h2>Register for Event</h2>

  <%
    String eventId = request.getParameter("eventId");
    String title = "Sample Event " + eventId;
    String date = "2025-10-0" + eventId;
    String time = "10:0" + eventId + " AM";
    String venue = "Main Hall";
  %>

  <table class="styled-table">
    <thead>
      <tr>
        <th>Title</th>
        <th>Date</th>
        <th>Time</th>
        <th>Venue</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><%= title %></td>
        <td><%= date %></td>
        <td><%= time %></td>
        <td><%= venue %></td>
      </tr>
    </tbody>
  </table>

  <form action="ConfirmRegistrationServlet" method="post" style="text-align:center; margin-top:20px;">
    <input type="hidden" name="eventId" value="<%= eventId %>">
    <button type="submit" class="btn-primary">Confirm Registration</button>
  </form>
</main>

<%@ include file="includes/footer.jsp" %>
