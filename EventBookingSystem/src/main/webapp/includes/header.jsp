<!-- includes/header.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Event Booking System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<header>
  <nav class="navbar">
    <div class="logo">CollegeConnect</div>
    <ul class="nav-links">
      <li><a href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
      <li><a href="<%= request.getContextPath() %>/about.jsp">About</a></li>
      <li><a href="<%= request.getContextPath() %>/help.jsp">Help</a></li>
      <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>

      <%
          String user = (String) session.getAttribute("username");
          String role = (String) session.getAttribute("role");

          if (user == null) {
      %>
          <!-- Guest (not logged in) -->
          <li><a href="<%= request.getContextPath() %>/login.jsp">Login</a></li>
          <li><a href="<%= request.getContextPath() %>/register.jsp" class="btn-primary">Register</a></li>

      <%
          } else if ("ADMIN".equalsIgnoreCase(role)) {
      %>
          <!-- Admin -->
          <li><a href="<%= request.getContextPath() %>/admindashboard">Dashboard</a></li>
          <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>

      <%
          } else {
      %>
          <!-- Normal User -->
          <li><a href="<%= request.getContextPath() %>/events">Events</a></li>
          <li><a href="<%= request.getContextPath() %>/myregistrations">My Registrations</a></li>
          <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
      <%
          }
      %>
    </ul>
  </nav>
</header>
