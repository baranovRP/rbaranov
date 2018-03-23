<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/add" method="post">
    <label for="name">Name: </label>
    <input type="text" name="name" id="name" value="">
    <label for="login">Login: </label>
    <input type="text" name="login" id="login" value="">
    <label for="email">Email: </label>
    <input type="text" name="email" id="email" value="">
    <input type="submit" value="Add">
</form>
</body>
</html>
