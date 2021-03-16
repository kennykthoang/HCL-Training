<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User ID</title>
</head>
	<body>
		<h3>Please enter the user ID</h3>
		<form action="userid" method="post">
			<label>
				<input type = "number" name="id" placeholder="User ID#" min="1">
				<input type = "submit" value= "submit">
			</label>
		</form>
	</body>
</html>