<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Todo List</title>
</head>
<body>

<!-- Step 1: Create HTML Form -->
<form action="todo-demo.jsp">
	Add new item: <input type="text" name="theItem" />
	
	<input type="submit" value="Submit" />
</form>

<!-- Step 2: Add new item to "To Do" List -->
<%
	// get the TO DO items from the session
	List<String> items = (List<String>) session.getAttribute("myToDoList");

	// if the TO DO items doesn't exist, then create a new one
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}
	
	// see if there is form data to add
	String theItem = request.getParameter("theItem");
	if (theItem != null) {
		items.add(theItem);
	}
%>

<!-- Step 3: Display all "To Do" item from session -->
<br/>
<b>To Do List Items:</b> <br/>
<hr>
<ol>
<%
	for (String temp : items) {
		out.println("<li>" + temp + "</li>");
	}

%>
</ol>

</body>
</html>