<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Success</title>
</head>
<body>
	Registration was successful!<br><br>
	Registration Details: <br>
	Name: ${user.name}<br>
	Email: ${user.email}<br>
	Password: ${user.password}<br>
	
	<a href='/'>Return to homepage</a><br>
</body>
</html>