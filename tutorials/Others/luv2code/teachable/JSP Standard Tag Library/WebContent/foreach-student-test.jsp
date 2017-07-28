<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, com.luv2code.jsp.demo.Student"%>
    
<% 
   	// just create some sample data ... normally provided by MVC
   	List<Student> data = new ArrayList<>();
   
   	data.add(new Student("John", "Doe", false));
   	data.add(new Student("Maxwell", "Johnson", false));
   	data.add(new Student("Mary", "Wallace", true));
   	
   	pageContext.setAttribute("myStudents", data);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Gold Customer</th>
	</tr>
	
	<c:forEach var="tempStudent" items="${myStudents}">
	
		<tr>
			<td>${tempStudent.firstName}</td>
			<td>${tempStudent.lastName}</td>
			<td>${tempStudent.goldCustomer}</td>
		</tr>
		
	</c:forEach>
	
	</table>
</body>
</html>