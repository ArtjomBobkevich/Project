<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.04.2019
  Time: 11:08
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
    <title><fmt:message key ="main_page.message" /></title>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>
<div>
    <br>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/resources-list"><fmt:message key ="resources_list.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/categories-list"><fmt:message key ="categories_list.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/genres-list"><fmt:message key ="genres.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/typefiles-list"><fmt:message key ="typefiles_list.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/save-resource"><fmt:message key ="save_resource.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/delete-resource"><fmt:message key ="delete_resource.message" /></a>
    <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath}/personList-info"><fmt:message key ="person_list.message" /></a>

</div>
<br>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/download"><fmt:message key ="download.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>