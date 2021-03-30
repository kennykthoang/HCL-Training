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
                    <td>Current Task Description:</td>
                    <td><textarea rows=10 cols=30 readonly="Readonly">${task.description}</textarea></td>
                    <td>&emsp;&emsp;&emsp;New Task Description:</td>
                    <td><textarea id="desc" name="desc" rows=10 cols=30></textarea></td>
                </tr>
                <tr>             
                    <td>Current Task Severity:</td>
					<td>
	                    <select id="oldsev" name="oldsev" disabled>
						  	<option>${task.severity}</option>
	  					</select>
  					</td>
                    <td>&emsp;&emsp;&emsp;New Task Severity:</td>
                    <td>
	                    <select id="sev" name="sev">
						  	<option value="High">High</option>
						  	<option value="Medium">Medium</option>
						  	<option value="Low">Low</option>
	  					</select>
  					</td>
                </tr>
                <tr>
                	<td>Current Start Date:</td>
                	<td><input type="text" value="${sDate}" readonly="Readonly"></td>
                	<td>&emsp;&emsp;&emsp;New Start Date:</td>
                	<td><input type="date" id="sdate" name="sdate" required></td>
                </tr>
                <tr>
                	<td>Current End Date:</td>
                	<td><input type="text" value="${eDate}" readonly="Readonly"></td>
                	<td>&emsp;&emsp;&emsp;New end Date:</td>
                	<td><input type="date" id="edate" name="edate" required></td>
                </tr>              
                <tr>
                    <td colspan="2"><button type="submit">Update</button> </td>
                </tr>
            </table>
        </form>
    </div>
	</body>
</html>