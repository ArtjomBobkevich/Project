<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.04.2019
  Time: 14:50
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
    <title><fmt:message key ="person_list.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div class="input-group mb-3">
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<div>
    <c:forEach var="personList" items="${requestScope.personList}">
        <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/person-info?login=${personList.login}"><fmt:message key ="person_info.message" /> ${personList.login}</a><br>
    </c:forEach>
</div>
<div class="input-group mb-3">
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
</div>
<div class="input-group mb-3">
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</div>
</body>
</html>
