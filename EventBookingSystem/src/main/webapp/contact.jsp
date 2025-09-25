<%@ include file="includes/header.jsp" %>

<main>
  <section class="page-section">
    <h1>Contact Us</h1>
    <p>We would love to hear from you! Reach us at:</p>
    <ul>
      <li>Email: support@eventbooking.com</li>
      <li>Phone: +91 9359-016144</li>
      <li>Location: Nagpur, Maharashtra</li>
    </ul>

    <form class="contact-form">
      <input type="text" placeholder="Your Name" required>
      <input type="email" placeholder="Your Email" required>
      <textarea placeholder="Your Message" rows="4" required></textarea>
      <button type="submit" class="btn-primary">Send Message</button>
    </form>
  </section>
</main>

<%@ include file="includes/footer.jsp" %>
