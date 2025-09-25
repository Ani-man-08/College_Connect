<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/includes/header.jsp" %>

<div class="container">
    <h2 class="page-title">👥 Participants</h2>
    <p class="subtitle">Event: <b><%= request.getAttribute("eventTitle") %></b></p>

    <%
        List<Map<String, Object>> participants = (List<Map<String, Object>>) request.getAttribute("participants");
        int total = (participants != null) ? participants.size() : 0;
    %>
    <p class="subtitle"><b>Total Participants: <%= total %></b></p>

    <table class="styled-table">
        <thead>
            <tr>
                <th>Student Name</th>
                <th>Department</th>
                <th>Registration Date</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (participants != null && !participants.isEmpty()) {
                for (Map<String,Object> p : participants) {
                    String username = (String) p.get("username");
                    String department = (String) p.get("department");
                    Object registeredAt = p.get("registered_at");
        %>
            <tr>
                <td><%= username %></td>
                <td><%= department %></td>
                <td><%= registeredAt != null ? registeredAt.toString() : "N/A" %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="3" class="no-data">No participants yet.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div class="center">
        <a href="<%= request.getContextPath() %>/admindashboard" class="btn">⬅ Back to Dashboard</a>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
