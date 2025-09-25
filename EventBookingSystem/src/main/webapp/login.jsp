<%@ include file="includes/header.jsp" %>

<main class="form-page">
  <div class="form-container">
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
      <label for="username">Username</label>
      <input type="text" id="username" name="username" required />

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required />

      <button type="submit" class="btn-primary">Login</button>
    </form>
    <p class="form-text">Do not have an account? <a href="register.jsp">Register</a></p>
  </div>
</main>

<%@ include file="includes/footer.jsp" %>
