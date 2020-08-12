<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Quizzer</title>
    </head>
    <body style="background-image: linear-gradient(to top left, #452283, #ffffff); text-align: center">
    <center>
        <p style="font-weight:bold">Register on Quizzer</p><br/>
        <c:if test="${error != null}">
            <label style="color:#ff0000">Registration Failed: ${error}</label>
        </c:if>
        <form method="POST">
            <label for="username">Username: </label>
            <input style="background-color:#0000; border:none" type="text" placeholder="Enter Username" name="username" value="${username}" /><br/>
            <label for="password">Password: </label>
            <input style="background-color:#0000; border:none" type="password" placeholder="Enter Password" name="password" /><br/>
            <button type="submit">Register</button>
        </form>
        <label>Already have an account? <a href="login" type="button">Log in</a> instead</label>
    </center>
    </body>
</html>
