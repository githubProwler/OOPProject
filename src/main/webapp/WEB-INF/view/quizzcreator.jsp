<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create a quizz</title>
</head>
<body style="background-image:linear-gradient(to top, #553293, #ffffff); text-align: center">
<div style="height: 100%; width: 100%; text-align: center">
    <div style="height: 100%; width: 20%; border-right-style: solid; float: left">
        <div>
            <a href ="/home">Home</a><br/>
            <a href="/logout">Log out</a>
        </div>
    </div>
    <div style="height: 100%; width: 70%; float: left">
        <div>
            <h1>Quizz Creator</h1>
        </div>
        <div style="overflow-y:auto; height: 30%">
            <c:if test="${error != null}">
                <h1 style="color:red">${error}</h1>
            </c:if>
            <c:if test="${error == null}">
                <div>
                    <form style="display: inline-block" action="/quizzcreator/addquestion" method="POST">
                        <label>Question: </label>
                        <input style="background-color:#0000; border:none" type="text" placeholder="Enter question here" name="question"/><br/>
                        <label>Answer: </label>
                        <input style="background-color:#0000; border:none" type="text" placeholder="Enter answer here" name="answer"/><br/>
                        <button type="submit">Add question</button>
                    </form>
                </div>

                <div>
                    <form style="display: inline-block" action="/quizzcreator" method="POST">
                        <label>Quizz name: </label>
                        <input style="background-color:#0000; border:none" type="text" placeholder="Enter quizz name here" name="name"/><br/>
                        <label>Quizz description: </label>
                        <input style="background-color:#0000; border:none" type="text" placeholder="Enter description here" name="description"/><br/>
                        <button type="submit">Save quizz</button>
                    </form><br/>
                    <form style="display: inline-block" action="/quizzcreator/reset" method="POST">
                        <button type="submit">Discard quizz</button>
                    </form>
                </div>
            </c:if>
        </div>
        <div style="overflow-y:auto; height: 50%">
            <label style="color:green; font-weight: bold">Quizz preview:</label><br/><br/>
            <c:forEach var="question" items="${sessionScope.quizz}">
                <div style="height:20px; vertical-align: center" >Question: ${question.question}</div>
                <div style="height:20px; vertical-align: center" >Answer: ${question.answer}</div><br/>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>