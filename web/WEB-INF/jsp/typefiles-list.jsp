<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 31.03.2019
  Time: 0:57
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
    <title><fmt:message key ="typefiles_list.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div class="input-group mb-3">
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<div class="input-group mb-3">
    <c:forEach var="typefile" items="${requestScope.typefiles}">
        <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/resources-by-typefile-list?id=${typefile.id}"><fmt:message key ="find_by.message" /> ${typefile.name}</a>
    </c:forEach>
</div>
<div class="input-group mb-3">
<a class="btn btn-success" href="${pageContext.request.contextPath}/typefile-save"><fmt:message key ="typefile_save.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/typefile-delete"><fmt:message key ="typefile_delete.message" /></a>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</div>
</body>
</html>