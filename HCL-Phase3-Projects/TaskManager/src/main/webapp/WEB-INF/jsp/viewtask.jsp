<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
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

<br style="line-height:6;">
<a href="mainmenu">Return to homepage</a>
</body>
</html>

