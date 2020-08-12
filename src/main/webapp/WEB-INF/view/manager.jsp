<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager</title>
</head>
<body style="background-image:linear-gradient(to top, #553293, #ffffff); text-align: center">
<div style="height: 100%; width: 100%; text-align: center">
    <div style="height: 100%; width: 20%; border-right-style: solid;float: left">
        <div>
            <a href ="/home">Home</a><br/>
            <a href="/logout">Log out</a>
        </div>
    </div>
    <div style="height: 100%; width: 70%; float: left">
        <div>
            <h1>Administration</h1>
        </div>
        <label style="font-size: 20px">Users</label><br/><br/>
        <div style="overflow-y:auto; height: 75%">
            <c:if test="${error != null}">
                <h1 style="color:red">${error}</h1>
            </c:if>
            <c:if test="${error == null}">
                <div style="width: 50%; float: left">
                    <c:forEach var="user" items="${users}">
                        <div style="height:30px; vertical-align: center" >${user.username}</div>
                    </c:forEach>
                </div>
                <div style="width: 50%; float: left">
                    <c:forEach var="user" items="${users}">
                        <div style="height:30px">
                            <form style="display: inline-block" action="/manager/delete/${user.username}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                            <c:if test="${user.userType != 0}">
                                <form style="display: inline-block" action="/manager/upgrade/${user.username}" method="POST">
                                    <button type="submit">UPGRADE</button>
                                </form>
                            </c:if>
                            <c:if test="${user.userType == 0}">
                                <form style="display: inline-block" action="/manager/downgrade/${user.username}" method="POST">
                                    <button type="submit">ჩამოალაბორანტე</button>
                                </form>
                            </c:if>
                            <br/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>