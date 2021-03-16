<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
</head>
	<body>
	<div>
        <h1>Update User</h1>
        <br />
        <form action="update" method="post">
 
            <table style = "padding:10">
                <tr>             
                    <td>User ID:</td>
                    <td><input type="text" value="${update.id}" name="id" readonly="readonly"></td>
                    <!--<td>&emsp;&emsp;&emsp;User ID(unchanged):</td>
                    <td><input type="text" value="${update.id}" readonly="Readonly"></td>-->
                </tr>
                <tr>             
                    <td>Current Name:</td>
                    <td><input type="text" value="${update.name}" readonly="Readonly"></td>
                    <td>&emsp;&emsp;&emsp;New Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>Current Email:</td>
                    <td><input type="text" value="${update.email}" readonly="Readonly"></td>
                    <td>&emsp;&emsp;&emsp;New Email:</td>
                	<td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td>Current Password:</td>
                    <td><input type="text" value="${update.password}" readonly="Readonly"></td>
                    <td>&emsp;&emsp;&emsp;New Password:</td>
                    <td><input type="text" name="password"></td>
                </tr>                           
                <tr>
                    <td colspan="2"><button type="submit">Update</button> </td>
                </tr>
            </table>
        </form>
    </div>
	</body>
</html>