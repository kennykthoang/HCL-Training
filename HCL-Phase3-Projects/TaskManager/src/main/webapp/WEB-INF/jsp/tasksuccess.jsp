<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Success</title>
</head>
<body>
	Task ${action} was successful!<br><br>
	Task Details: <br>
	Name: ${task.name}<br>
	<!-- Following prints out the dates as a formatted string -->
	Start Date: ${sDate}<br>
	End Date: ${eDate}<br>
	<!-- Following prints out the Date object from task -->
	<%-- Start Date: ${task.startDate}<br>
	End Date: ${task.endDate}<br> --%>
	Severity: ${task.severity}<br>
	Description: ${task.description}<br>
	Owner: ${user.name}<br>
	
	<br style="line-height:6;">
	
	<a href='mainmenu'>Return to homepage</a><br>
</body>
</html>