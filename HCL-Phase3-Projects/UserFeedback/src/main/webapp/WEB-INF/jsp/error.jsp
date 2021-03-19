<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table td{
vertical-alight: top;
border: solid 1px #888;
padding: 10px;
}
</style>
<meta charset="UTF-8">
<title>Error</title>
</head>
	<body>
		<h3 style="background-color:red">Error Page!</h3>
		
		<h2> Error: ${message} </h2>
		
		<a href="/">Return to main page</a>
	</body>
</html>