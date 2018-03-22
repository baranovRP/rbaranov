<%@ page import="ru.job4j.userapp.User" %>
<%@ page import="ru.job4j.userapp.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/add.jsp">Add new user</a>
<ul>
    <% for (User user : UserStore.getInstance().getAll()) { %>
    <li>
        <form action="<%=request.getContextPath()%>/update" method="post">
            <label for="name-<%=user.getId()%>">Name: </label>
            <input type="text" name="name" id="name-<%=user.getId()%>"
                   value="<%=user.getName()%>">
            <label for="login-<%=user.getId()%>">Login: </label>
            <input type="text" name="login" id="login-<%=user.getId()%>"
                   value="<%=user.getLogin()%>">
            <label for="email-<%=user.getId()%>">Email: </label>
            <input type="text" name="email" id="email-<%=user.getId()%>"
                   value="<%=user.getEmail()%>">
            <input type="hidden" id="id-<%=user.getId()%>" name="id"
                   value="<%=user.getId()%>">
            <input type="submit" value="Edit">
        </form>
        <form action="<%=request.getContextPath()%>/remove" method="post">
            <input type="hidden" id="id-<%=user.getId()%>" name="id"
                   value="<%=user.getId()%>">
            <input type="submit" value="Remove">
        </form>
    </li>
    <% } %>
</ul>

</body>
</html>
