<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/add">Add new user</a>
<ul>
    <c:forEach items="${users}" var="user">
        <li>
            <form action="${pageContext.servletContext.contextPath}/update"
                  method="post">
                <label for="<c:out value="name-${user.id}"/>">Name: </label>
                <input type="text" name="name"
                       id="<c:out value="name-${user.id}"/>"
                       value="<c:out value="${user.name}"/>">
                <label for="<c:out value="login-${user.id}"/>">Name: </label>
                <input type="text" name="login"
                       id="<c:out value="login-${user.id}"/>"
                       value="<c:out value="${user.login}"/>">
                <label for="<c:out value="email-${user.id}"/>">Name: </label>
                <input type="text" name="email"
                       id="<c:out value="email-${user.id}"/>"
                       value="<c:out value="${user.email}"/>">
                <input type="hidden" name="id"
                       id="<c:out value="id-${user.id}"/>"
                       value="<c:out value="${user.id}"/>">
                <input type="submit" value="Edit">
            </form>
            <form action="${pageContext.servletContext.contextPath}/remove"
                  method="post">
                <input type="hidden" name="id"
                       id="<c:out value="id-${user.id}"/>"
                       value="<c:out value="${user.id}"/>">
                <input type="submit" value="Remove">
            </form>
        </li>
    </c:forEach>
</ul>

</body>
</html>
