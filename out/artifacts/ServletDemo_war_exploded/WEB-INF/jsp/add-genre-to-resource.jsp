<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 30.03.2019
  Time: 21:10
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
    <title><fmt:message key ="add_genre_to_resource.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<form action="${pageContext.request.contextPath}/add-genre-to-resource" method="post">
    <div>
        <br>
        <select class="custom-select" name="resourceId" id="resourceId">
            <c:forEach var="resourceId" items="${requestScope.resourcesName}">
                <option value="${resourceId.id}">${resourceId.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select class="custom-select" name="genreId" id="genreId">
            <c:forEach var="genreId" items="${requestScope.genresName}">
                <option value="${genreId.id}">${genreId.name}</option>
            </c:forEach>
        </select>
    </div>
    <br>
    <input class="btn btn-success" type="submit" value="<fmt:message key ="save_name.message" />">
</form>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>
