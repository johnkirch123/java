<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Demo</title>
</head>
<body>
	<h3>Hello World of Java</h3>
	The time on the server is : <%= new java.util.Date() %>
	<br><br>
	Converting a string to uppercase: <%= new String("This was once a lower case statement.").toUpperCase() %>
	<br><br>
	Five times eight is : <%= 5 * 8 %>
	<br><br>
	Is 75 less than 69? <%= 75 < 69 %>
	<br><br>
	
	<!-- JSP scriplets -->
	<!-- syntax <% %> -->
	
	<%
		for (int i = 1; i <= 4; i++) {
			// Minimize the amount of scriplet code in a jsp.
			// If you have a lot of code use MVC or use a separate java class.
			out.println("I REALLY love to code!!!");
		}
	%>
	<br><br>
	
	<!-- JSP declarations - declare a method -->
	<!-- syntax <%! %> -->
	
	<%!
		String makeItLower (String data) {
			return data.toLowerCase();
		}
	%>
	
	Lower case "HELLO WORLD": <%= makeItLower("HELLO WORLD") %>
	<br><br>
	
	
	
</body>
</html>