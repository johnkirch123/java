<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// just create some sample data ... normally provided by MVC
	String[] cities = {"Mumbai", "Singapore", "Georgia", "Hong Kong"};

	pageContext.setAttribute("myCities", cities);

%>
<html>

<head>



</head>
<body>

	<c:forEach var="tempCity" items="${myCities}">
	
		${tempCity} <br/>
	
	</c:forEach>

</body>


</html>