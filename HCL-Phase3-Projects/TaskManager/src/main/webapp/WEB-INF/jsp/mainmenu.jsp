<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Menu</title>
</head>
<body>
	<h2 class="index-title">Task Menu</h2>
	1. <a href='/task'>Create Task</a><br>
	2. <a href='/task-view'>View Tasks</a><br>
	3. <a href='/task-update'>Update Task</a><br>
	4. <a href='/task-delete'>Delete Tasks</a><br>
	<br style="line-height:6;">
	<form action="logout" method="get">
        <input name="logout" type="submit" value="Log Out" />
    </form>
</body>
</html>