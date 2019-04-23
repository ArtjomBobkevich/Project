<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 01.04.2019
  Time: 16:03
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
    <title><fmt:message key ="save_comment.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<form action="${pageContext.request.contextPath}/save-comment" method="post">
    <div>
        <select class="custom-select" name="resourceId" id="resourceId">
            <c:forEach var="resourceId" items="${requestScope.resources}">
                <option value="${resourceId.id}">${resourceId.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <div class="input-group-text">
        <label for="text"><fmt:message key ="comment.message" />
            <input  id="text" type="text" name="text" class="form-control"/>
        </label><br>
    </div>
        </div>
    </div>
    <input type="submit" value="<fmt:message key ="save_name.message" />">
</form>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>
