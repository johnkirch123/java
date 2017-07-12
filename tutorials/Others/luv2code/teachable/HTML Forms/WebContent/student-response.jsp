<html>

<head><title>Student Confirmation Title</title></head>

<body>

	The student is confirmed: ${param.firstName} ${param.lastName}
	<br>
	The students origin is: ${param.country}
	<br>
	The students favorite langauge is: ${param.favoriteLanguage}
	<br>
	The students favorite langauges are: 
	<ul>
		<%
			String[] langs = request.getParameterValues("favoriteLanguages");
		
			for (String tempLang : langs) {
				out.println("<li>" + tempLang + "</li>");
			}
		
		%>
	</ul>

</body>
</html>