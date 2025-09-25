<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.*,java.text.SimpleDateFormat" %>
<%@ include file="includes/header.jsp" %>

<main class="events">
  <h2>Upcoming Events</h2>

  <table class="styled-table">
    <thead>
      <tr>
        <th>Title</th>
        <th>Category</th>
        <th>Date</th>
        <th>Time</th>
        <th>Venue</th>
        <th>Capacity</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Map<String, Object>> events = (List<Map<String, Object>>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");

            for (Map<String, Object> e : events) {
      %>
      <tr>
        <td><%= e.get("title") %></td>
        <td><%= e.get("category") %></td>
        <td><%= e.get("event_date") != null ? df.format(e.get("event_date")) : "" %></td>
        <td><%= e.get("event_time") != null ? tf.format(e.get("event_time")) : "" %></td>
        <td><%= e.get("venue") %></td>
        <td><%= e.get("capacity") %></td>
        <td>
          <a href="RegisterEventServlet?eventId=<%= e.get("event_id") %>" class="btn-primary">Register</a>
        </td>
      </tr>
      <%
            }
        } else {
      %>
      <tr>
        <td colspan="7" style="text-align:center;">No events found.</td>
      </tr>
      <% } %>
    </tbody>
  </table>
</main>

<%@ include file="includes/footer.jsp" %>
