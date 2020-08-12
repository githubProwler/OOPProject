<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Quizzer</title>
    </head>
    <body style="background-image: linear-gradient(to top left, #452283, #ffffff); text-align: center">
        <p style="font-weight:bold">Welcome<br/>Please log in</p><br/>
        <c:if test="${error != null}">
            <label style="color:#ff0000">Authentication error: ${error}</label><br/>
        </c:if>
        <form class="modal-content animate" method="POST">
            <label for="username">Username: </label>
            <input style="background-color:#0000; border:none" type="text" placeholder="Enter Username" name="username" value="${username}" /><br/>
            <label for="password">Password: </label>
            <input style="background-color:#0000; border:none" type="password" placeholder="Enter Password" name="password" /><br/>
            <button type="submit">Login</button>
        </form>
        <label>Don't have an account? <a href="register" type="button">Register</a></label>
    </body>
</html>
