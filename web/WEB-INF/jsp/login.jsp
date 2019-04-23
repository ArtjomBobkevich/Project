<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 27.03.2019
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <style>
        body {
            background: url(/wallpaper.jpg);
        }
    </style>
    <%@include file="style.jsp"%>
    <fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key ="authorization.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="form-group">
        <label for="login"><fmt:message key ="username.message" /><br>
            <input class="form-control" id="login" type="text" name="login" aria-describedby="emailHelp" placeholder="Enter login"/>
            <small id="emailHelp" class="form-text text-muted">Please, enter your login.</small>
        </label><br>
    </div>
    <%--<c:if test="${param.error}">--%>
        <%--<span> Вы ввели не корректно логин</span><br>--%>
    <%--</c:if>--%>
    <div class="form-group">
        <label for="password"><fmt:message key ="password.message" />
            <input class="form-control" id="password" type="password" name="password" aria-describedby="passwordHelp" placeholder="Enter password"/>
            <small id="passwordHelp" class="form-text text-muted">Please, enter your easy password.</small>
        </label><br>
    </div>
    <input class="btn btn-primary" type="submit" value="<fmt:message key ="enter.message" />">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/save-person"><fmt:message key ="registration.message" /></a>
</form>

</body>
</html>
