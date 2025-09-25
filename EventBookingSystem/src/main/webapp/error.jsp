<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ include file="/includes/header.jsp" %>

<h2 style="color:red;">Oops! Something went wrong.</h2>
<p><b>Error Message:</b> <%= exception != null ? exception.getMessage() : request.getParameter("msg") %></p>

<div class="center">
    <a class="btn" href="index.jsp">⬅ Go Home</a>
</div>

<%@ include file="/includes/footer.jsp" %>
