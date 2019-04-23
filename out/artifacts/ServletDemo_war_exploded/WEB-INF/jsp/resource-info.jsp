<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 26.03.2019
  Time: 23:57
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
    <title><fmt:message key ="resource_info.message" /></title>
</head>
<body>
<%@include file="style.jsp"%>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'en_UK'}"/>
<fmt:setBundle basename="messages"/>
<div>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=en_UK">ENG</a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/locale?language=ru_RU">RUS</a>

</div>

<fmt:message key ="resource_info_name.message" />: <span>${requestScope.resource.resourceName}</span><br>
<fmt:message key ="resource_info_type.message" />: <span>${requestScope.resource.typeFile}</span><br>
<fmt:message key ="resource_info_category.message" />: <span>${requestScope.resource.category}</span><br>
<fmt:message key ="resource_info_author.message" />: <span>${requestScope.resource.person}</span><br>
<fmt:message key ="resource_info_link.message" />: <a class="btn btn-outline-success btn-sm" href="/resources/${requestScope.resource.url}">${requestScope.resource.url}</a><br>
<fmt:message key ="resource_info_size.message" />: <span>${requestScope.resource.size}</span><br>
<a class="btn btn-success" href="${pageContext.request.contextPath}/add-genre-to-resource"><fmt:message key ="add_genre_to_resource.message" /></a>
<a class="btn btn-success" href="${pageContext.request.contextPath}/save-comment"><fmt:message key ="save_comment.message" /></a>
<a class="btn btn-success" href="${pageContext.request.contextPath}/all-comment-by-resource?id=${requestScope.resource.id}"><fmt:message key ="all_comment_by_resource.message" /></a>
<br><br>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/comment-delete"><fmt:message key ="delete_comment.message" /></a>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/begin"><fmt:message key ="return.message" /></a>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/logout"><fmt:message key ="logout.message" /></a>
</body>
</html>
