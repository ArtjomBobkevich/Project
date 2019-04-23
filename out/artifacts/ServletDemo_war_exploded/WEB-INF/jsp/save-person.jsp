<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.04.2019
  Time: 14:06
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
    <fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
    <fmt:setBundle basename="messages"/>
    <%@include file="style.jsp"%>
    <title><fmt:message key ="registration.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div class="input-group mb-3">
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<form action="${pageContext.request.contextPath}/save-person" method="post">
    <div>
        <label for="login_name"><fmt:message key ="login_name.message" />
            <input class="form-control" id="login_name" type="text" name="login_name" required placeholder="Login"/>
        </label><br>
    </div>
    <div>
        <label for="firstName"><fmt:message key ="firstName.message" />
            <input class="form-control" id="firstName" type="text" name="firstName" required placeholder="firstName"/>
        </label><br>
    </div>
    <div>
        <label for="lastName"><fmt:message key ="lastName.message" />
            <input class="form-control" id="lastName" type="text" name="lastName" required placeholder="lastName"/>
        </label><br>
    </div>
    <div>
        <label for="age"><fmt:message key ="age.message" />
            <input class="form-control" id="age" type="number" name="age" required placeholder="age"/>
        </label><br>
    </div>
    <div>
        <label for="mail"><fmt:message key ="mail.message" />
            <input class="form-control" id="mail" type="text" name="mail" required placeholder="mail"/>
        </label><br>
    </div>
    <div>
        <label for="password"><fmt:message key ="password_name.message" />
            <input class="form-control" id="password" type="password" name="password" required placeholder="password"/>
        </label><br>
    </div>
    <div class="input-group mb-3">
    <input class="btn btn-success" type="submit" value="<fmt:message key ="save_name.message" />">
    </div>
    <div class="input-group mb-3">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
        </div>
        <div class="input-group mb-3">
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
        </div>

</form>
</body>
</html>
