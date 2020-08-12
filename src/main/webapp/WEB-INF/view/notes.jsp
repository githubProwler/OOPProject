<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
    <center>
        <a href="/logout">Logout</a></br>
        Hi ${sessionScope.user.username}
        <ul>
            <c:forEach var="note" items="${notes}">
                <li>
                    <form action="/notes/${note.id}" method="POST">
                        <button type="submit">Delete</button>
                    </form>
                        ${note.text}</li>
            </c:forEach>
        </ul>
        <form method="POST">
            <textarea name="text"></textarea><br/>
            <button type="submit">Add note</button>
        </form>
    </center>
    </body>
</html>