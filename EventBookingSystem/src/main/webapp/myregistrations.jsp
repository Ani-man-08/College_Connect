<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.List,java.util.Map" %>
<%@ include file="includes/header.jsp" %>

<main class="events">
  <h2>My Registrations</h2>

  <table class="styled-table">
    <thead>
      <tr>
        <th>Event</th>
        <th>Date</th>
        <th>Venue</th>
        <th>Registration Date</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Map<String, Object>> registrations = (List<Map<String, Object>>) request.getAttribute("registrations");
        if (registrations != null && !registrations.isEmpty()) {
            for (Map<String, Object> reg : registrations) {
      %>
      <tr>
        <td><%= reg.get("title") %></td>
        <td><%= reg.get("event_date") %></td>
        <td><%= reg.get("venue") %></td>
        <td><%= reg.get("registration_date") %></td>
        <td>Registered</td>
      </tr>
      <%
            }
        } else {
      %>
      <tr>
        <td colspan="5" style="text-align:center;">No registrations found.</td>
      </tr>
      <% } %>
    </tbody>
  </table>
</main>

<%@ include file="includes/footer.jsp" %>
