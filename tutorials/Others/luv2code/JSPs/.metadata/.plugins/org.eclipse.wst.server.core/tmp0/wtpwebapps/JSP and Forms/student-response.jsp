<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Information</title>
</head>
<body>
	<h3 align="center"><b>Student information</b></h3>
	<!-- The student is confirmed: <%= request.getParameter("firstName") %> <%= request.getParameter("lastName") %> -->
	<br><br>
	The student is confirmed: ${param.firstName} ${param.lastName}
	<br><br>
	Students favorite language: ${param.favoriteLanguage}
	<br><br>
	
	Students favorite languages:
	<!-- Display list of "favoriteLanguages" -->
	<ul>
	
		<%
		
			String[] langs = request.getParameterValues("favoriteLanguages");
		
			for (String tempLang : langs) {
				out.println("<li>" + tempLang + "</li>");
			}
		
		%>
	
	</ul>
	
	<br><br>
	Students country of origin: ${param.country}
</body>
</html>