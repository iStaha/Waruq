<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
		<h2>
			<a href="new">Add New User</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Users</a>

		</h2>
	</center>
	<div align="center">


		<form action="add" method="post">

			<table border="1" cellpadding="5">
				<caption>
					<h2>Add Product</h2>
				</caption>

				<tr>
					<th>Item:</th>
					<td><input type="text" name="item" size="45"
						value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>