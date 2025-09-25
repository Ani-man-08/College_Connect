<%@ include file="includes/header.jsp" %>

<main>
  <!-- Hero Section -->
  <section class="hero">
    <div class="hero-text">
      <h1>Discover & Book Exciting Events</h1>
      <p>Join, register, and track events organized by your institution easily and securely.</p>
      <div class="hero-buttons">
        <a href="events.jsp" class="btn-primary">Explore Events</a>
        <a href="about.jsp" class="btn-secondary">Learn More</a>
      </div>
    </div>
    <div class="hero-img">
      <img src="<%= request.getContextPath() %>/assets/images/h_sec.jpg" alt="...">

    </div>
  </section>

  <!-- Why Choose Us -->
  <section class="why-us">
    <h2>Why Choose Us?</h2>
    <div class="cards">
      <div class="card">
        <h3>Easy Registration</h3>
        <p>Register for events with just one click.</p>
      </div>
      <div class="card">
        <h3>Track Participation</h3>
        <p>View your event history and participation records anytime.</p>
      </div>
      <div class="card">
        <h3>Secure Access</h3>
        <p>All your data is safe with us.</p>
      </div>
    </div>
  </section>

 
</main>

<%@ include file="includes/footer.jsp" %>
