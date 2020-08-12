<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body style="background-image:linear-gradient(to top, #553293, #ffffff); text-align: center">
<div style="height: 100%; width: 100%; text-align: center">
    <div style="height: 100%; width: 20%; border-right-style: solid;float: left">
        <c:if test="${sessionScope.user.userType == 0}">
            <a href="/manager">Administer site</a><br/>
            <a href="/quizzcreator">Create a quizz</a><br/>
        </c:if>
        <a style="" href="/logout">Log out</a>
    </div>
    <div style="height: 100%; width: 70%; float: left">
        <h1>Welcome ${sessionScope.user.username}</h1>
        <div>
            <h3>Quizzes available</h3>
            <c:forEach var="quizz" items="${quizzes}">
                    <a href="/quizzviewer/${quizz.id}">Name: ${quizz.name};  Description: ${quizz.description};  Creator id: ${quizz.userId}; </a>
                </form>
                <br/>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>