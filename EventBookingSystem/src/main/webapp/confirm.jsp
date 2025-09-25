<%@ include file="includes/header.jsp" %>

<main class="events">
  <h2>Registration Successful</h2>

  <%
    String eventId = (String) request.getAttribute("eventId");
    if (eventId == null) {
        eventId = "Unknown";
    }
  %>

  <table class="styled-table">
    <thead>
      <tr>
        <th>Event ID</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><%= eventId %></td>
        <td>Registered Successfully ✅</td>
      </tr>
    </tbody>
  </table>

  <div style="text-align:center; margin-top:20px;">
    <a href="events.jsp" class="btn-primary">Back to Events</a>
    <a href="myregistrations.jsp" class="btn-secondary">My Registrations</a>
  </div>
</main>

<%@ include file="includes/footer.jsp" %>
