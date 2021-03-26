<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
        <title>Hello</title>
    </head>
    <body>
        <h1>Hello!</h1>
        
        <form action="users" method="get">
        <input name="users" type="submit" value="View Users"/>
        </form>
        <br>
        <form action="logout" method="get">
        <input name="logout" type="submit" value="Log Out" />
        </form>
        
        
    </body>
</html>