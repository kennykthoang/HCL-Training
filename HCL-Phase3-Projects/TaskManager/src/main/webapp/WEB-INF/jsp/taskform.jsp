<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task</title>
</head>
<body>

<form action="task" method="post">
 <label for="name">Task name:</label><br>
  <input type="text" id="name" name="name"><br>
  <label for="sdate">Start Date:</label><br>
  <input type="date" id="sdate" name="sdate" required><br>
  <label for="edate">End Date:</label><br>
  <input type="date" id="edate" name="edate" required><br>
  <label for="desc">Description:</label><br>
  <textarea id="desc" name="desc" rows=10 cols=30></textarea><br>
  <label for="sev">Severity:</label><br>
  <select id="sev" name="sev">
  	<option value="high">High</option>
  	<option value="medium">Medium</option>
  	<option value="low">Low</option>
  </select>
  
  
  
   
  <br>
  <br style="line-height:6;">
  <input type="submit" value="Submit">

</form>
</body>
</html>