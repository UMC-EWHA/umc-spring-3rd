<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ADD</title>

</head>

<body>

	<h1>ADD</h1>

	<form action="./add" method="post">

        <label>postnum</label> <input type="text" name="postnum"><br>
		<label>title</label> <input type="text" name="title"><br>
		<label>content</label> <input type="text" name="content"><br>
        <label>userid</label> <input type="text" name="userid"><br>

		<input type="submit" value="저장">

	</form>

</body>

</html>