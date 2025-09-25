<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.List,java.util.Map" %>
<%@ include file="includes/header.jsp" %>

<main class="events">
  <h2>Admin Dashboard</h2>

  <div class="admin-actions">
    <a href="addevent.jsp" class="btn-primary">+ Add Event</a>
  </div>

  <table class="styled-table">
    <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Date</th>
        <th>Venue</th>
        <th>Capacity</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <%
        // ✅ Safely get the events list
        List<Map<String, Object>> events = (List<Map<String, Object>>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
            for (Map<String, Object> e : events) {
                String eventId = String.valueOf(e.get("event_id"));
      %>
      <tr>
        <td><%= eventId %></td>
        <td><%= e.get("title") != null ? e.get("title") : "" %></td>
        <td><%= e.get("event_date") != null ? e.get("event_date") : "" %></td>
        <td><%= e.get("venue") != null ? e.get("venue") : "" %></td>
        <td><%= e.get("capacity") != null ? e.get("capacity") : "" %></td>
        <td>
          <!-- ✅ Dynamic eventId in query string -->
          <a href="EditEventServlet?eventId=<%= eventId %>" class="btn-secondary">✏️ Edit</a>
          <a href="DeleteEventServlet?eventId=<%= eventId %>" class="btn-danger" onclick="return confirm('Are you sure you want to delete this event?')">🗑️ Delete</a>
          <a href="participants.jsp?eventId=<%= eventId %>" class="btn-info">👥 View Participants</a>
        </td>
      </tr>
      <%
            }
        } else {
      %>
      <tr>
        <td colspan="6" style="text-align:center; font-style:italic;">No events found.</td>
      </tr>
      <% } %>
    </tbody>
  </table>
</main>

<%@ include file="includes/footer.jsp" %>
