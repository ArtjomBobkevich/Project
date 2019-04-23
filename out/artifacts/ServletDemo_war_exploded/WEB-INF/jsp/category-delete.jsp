<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 31.03.2019
  Time: 0:29
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
    <title><fmt:message key ="category_delete.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<form action="${pageContext.request.contextPath}/category-delete" method="post">
    <div>
        <select class="custom-select" name="categoryId" id="categoryId">
            <c:forEach var="categoryId" items="${requestScope.categories}">
                <option value="${categoryId.id}">${categoryId.name}</option>
            </c:forEach>
        </select>
    </div>
    <input class="btn btn-danger" type="submit" value="<fmt:message key ="delete_name.message" />">
</form>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a><br>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>
