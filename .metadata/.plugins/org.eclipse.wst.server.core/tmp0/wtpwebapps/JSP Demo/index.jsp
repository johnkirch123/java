<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Builtin Test</title>
</head>
<body>
	<h3>JSP Built-in Objects</h3>
	Request user agent: <%= request.getHeader("User-Agent") %>
	
	<br><br>
	
	Request language: <%= request.getLocale() %>
	<div class="container-fluid">
	  <div class="jumbotron">
	    <div class="row">
	      <h1 class="text-center text-primary">Elon Musk</h1>
	      <img src="http://i.telegraph.co.uk/multimedia/archive/02778/MUSKsum_2778344b.jpg" alt="Image of Elon Musk" class="image-responsive center-block">
	      <div style="background-color:lightgrey">
	        
	      </div>
	    </div>  
	  </div>
</div>
	
</body>
</html>