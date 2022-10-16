<%@page import="com.entities.User"%>
<%

	User user = (User)session.getAttribute("current-user");
	if(user == null){
		session.setAttribute("message", "You are not logged in !! Login first");
		response.sendRedirect("login.jsp");
		return;
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Normal</title>
	<%@include file="components/common_css_js.jsp"	%>
	
</head>
<body>
	<%@include file="components/navbar.jsp" %>
	
	<h1>This is normal user pannel</h1>
	
</body>
</html>