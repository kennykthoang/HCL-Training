<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Feedback</title>
<style>
textarea#comments
{
	font-family: Calibri, sans-serif;
	resize: none;
}
</style>
</head>

<body>

<h2>Feedback Form</h2>
<form action="addfeedback" method="post">
  <label for="name">Name:</label><br>
  <input type="text" id="user" name="user"><br><br>
  <label for="rating">Rating:</label><br>
  <input type="number" value = "1" step="1" min="1" max="10" id="rating" name="rating"><br><br>
  <label for="comment">Comments:</label><br>
  <!--<input type="text" id="comments" name="comments" rows = "4" cols="20"><br><br>-->
  <textarea id="comments" name="comments" rows="4" cols="50" placeholder="Please be honest!"></textarea><br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>
