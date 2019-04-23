<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 27.03.2019
  Time: 8:42
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
    <title><fmt:message key ="resources_list.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>
</div>
<div>
<c:forEach var="resource" items="${requestScope.resources}">
    <a >${resource.resourceName}</a><br>
    <a >${resource.typeFile}</a><br>
    <a >${resource.category}</a><br>
    <a class="btn btn-outline-success btn-sm"  href="${pageContext.request.contextPath}/resource-info?id=${resource.id}"><fmt:message key ="resource_info.message" /></a><br>
</c:forEach>
</div>
<br>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
<a class="btn btn-success" href="${pageContext.request.contextPath}/commentaries-list"><fmt:message key ="commentaries.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>
