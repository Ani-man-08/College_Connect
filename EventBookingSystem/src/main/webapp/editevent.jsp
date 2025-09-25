<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*,com.eventbooking.util.DBUtil" %>
<%@ include file="includes/header.jsp" %>

<%
    // ✅ Get eventId from request
    String eventIdStr = request.getParameter("eventId");
    int eventId = (eventIdStr != null) ? Integer.parseInt(eventIdStr) : -1;

    String title = "";
    String category = "";
    String eventDate = "";
    String eventTime = "";
    String venue = "";
    String capacity = "";
    String departmentId = "";

    if (eventId != -1) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM events WHERE event_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                title = rs.getString("title");
                category = rs.getString("category");
                eventDate = rs.getString("event_date");
                eventTime = rs.getString("event_time");
                venue = rs.getString("venue");
                capacity = rs.getString("capacity");
                departmentId = rs.getString("department_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<main class="form-page">
  <div class="form-container">
    <h2>Edit Event</h2>

    <form action="UpdateEventServlet" method="post">
      <!-- ✅ Hidden event ID -->
      <input type="hidden" name="event_id" value="<%= eventId %>">

      <input type="text" name="title" placeholder="Event Title" value="<%= title %>" required>
      <input type="text" name="category" placeholder="Category" value="<%= category %>" required>
      <input type="date" name="date" value="<%= eventDate %>" required>
      <input type="time" name="time" value="<%= eventTime %>" required>
      <input type="text" name="venue" placeholder="Venue" value="<%= venue %>" required>
      <input type="number" name="capacity" placeholder="Capacity" value="<%= capacity %>" required>
      <input type="text" name="department_id" placeholder="Department ID" value="<%= departmentId %>">

      <button type="submit" class="btn-primary">Update Event</button>
    </form>

    <p class="form-text"><a href="admindashboard">← Back to Dashboard</a></p>
  </div>
</main>

<%@ include file="includes/footer.jsp" %>
