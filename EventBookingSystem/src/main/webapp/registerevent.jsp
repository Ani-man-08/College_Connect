<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/header.jsp" %>

<style>
    .register-container {
        min-height: 70vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
        padding: 40px 20px;
    }
    .register-container h2 {
        font-size: 2rem;
        margin-bottom: 20px;
        color: #333;
    }
    .register-container p {
        font-size: 1.2rem;
        margin-bottom: 30px;
    }
    .btn-container {
        display: flex;
        gap: 20px;
    }
    .btn {
        padding: 10px 20px;
        text-decoration: none;
        background: #007BFF;
        color: #fff;
        border-radius: 6px;
        transition: 0.3s;
    }
    .btn:hover {
        background: #0056b3;
    }
</style>

<div class="register-container">
    <h2>Event Registration</h2>

    <p><%= request.getAttribute("message") %></p>

    <div class="btn-container">
        <a class="btn" href="events">🔙 Back to Events</a>
        <a class="btn" href="myregistrations">📋 My Registrations</a>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
