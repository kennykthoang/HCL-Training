<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
</head>
<body>
<h2>Users</h2>

<table style="float:left">
   <tr><th>ID</th><th>Username</th></th><th>Password</th></tr>
   <c:forEach items="${users}" var="user" varStatus="count">
    <tr id="${count.index}">
    	<td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
    </tr>
  </c:forEach>
</table>

<br style="line-height:6;">

<a href="/hello">Return to main page</a>

</body>
</html>

