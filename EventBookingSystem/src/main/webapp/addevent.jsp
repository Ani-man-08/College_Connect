<%@ include file="includes/header.jsp" %>

<main class="form-page">
  <div class="form-container">
    <h2>Add New Event</h2>

    <form action="AddEventServlet" method="post">
      <input type="text" name="title" placeholder="Event Title" required>
      <input type="text" name="category" placeholder="Category" required>
      <input type="date" name="date" required>
      <input type="time" name="time" required>
      <input type="text" name="venue" placeholder="Venue" required>
      <input type="number" name="capacity" placeholder="Capacity" required>

      <!-- ✅ Department selection (string) -->
     <label for="department">Department</label>
<select name="department" id="department" required>
  <option value="">-- Select Department --</option>
  <option value="1">Computer Science</option>
  <option value="2">Electronics</option>
  <option value="3">Mechanical</option>
  <option value="4">Civil</option>
  <option value="5">IT</option>
</select>


      <button type="submit">Add Event</button>
    </form>

    <p class="form-text">
      <a href="admindashboard.jsp">← Back to Dashboard</a>
    </p>
  </div>
</main>

<%@ include file="includes/footer.jsp" %>
