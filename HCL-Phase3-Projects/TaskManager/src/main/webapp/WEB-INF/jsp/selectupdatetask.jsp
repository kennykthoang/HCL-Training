<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
<meta charset="UTF-8">
<title>Select Task ID</title>
</head>
	<body>
	<h2>Tasks</h2>
	<table>
   <tr><th>ID</th><th>Name</th><th>Start Date</th><th>End Date</th><th>Severity</th><th>Description</th></tr>
   <c:forEach items="${tasks}" var="task">
    <tr id="${count.index}">
    	<td>${task.id}</td>
        <td>${task.name}</td>
        <td>${task.startDate}</td>
        <td>${task.endDate}</td>
        <td>${task.severity}</td>
        <td>${task.description}</td>
    </tr>
  	</c:forEach>
	</table>
	
		<h3>Please enter the task ID to update:</h3>
		<form action="task-update-select" method="post">
			<label>
				<input type = "number" name="id" placeholder="Task ID#" min="1">
				<input type = "submit" value= "submit">
			</label>
		</form>
	</body>
</html>