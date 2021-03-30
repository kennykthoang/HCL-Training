<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Task ID</title>
</head>
	<body>
		<h3>Please enter the task ID to update:</h3>
		<form action="task-update-select" method="post">
			<label>
				<input type = "number" name="id" placeholder="Task ID#" min="1">
				<input type = "submit" value= "submit">
			</label>
		</form>
	</body>
</html>