<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
    <label for="name">Name: </label>
    <input type="text" name="name" id="name" value="">
    <label for="login">Login: </label>
    <input type="text" name="login" id="login" value="">
    <label for="email">Email: </label>
    <input type="text" name="email" id="email" value="">
    <input type="hidden" id="id" name="id" value="">
    <input type="submit" value="Add">
</form>
</body>
</html>
