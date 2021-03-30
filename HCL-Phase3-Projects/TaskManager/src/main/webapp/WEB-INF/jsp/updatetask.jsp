<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Task</title>
</head>
	<body>
	<div>
        <h1>Update Task</h1>
        <br />
        <form action="task-update" method="post">
 
            <table style = "padding:10">
                <tr>             
                    <td>User ID:</td>
                    <td><input type="text" value="${task.id}" name="id" readonly="readonly"></td>
                </tr>
                <tr>             
                    <td>Current Task Name:</td>
                    <td><input type="text" value="${task.name}" readonly="Readonly"></td>
                    <td>&emsp;&emsp;&emsp;New Task Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>                          
                <tr>
                    <td colspan="2"><button type="submit">Update</button> </td>
                </tr>
            </table>
        </form>
    </div>
	</body>
</html>