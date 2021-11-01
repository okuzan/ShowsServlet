<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>USER</title>

</head>
<body>
<%@include  file="../fragments/headers/general.jsp" %>
<%--<%@include  file="../fragments/js/head.html" %>--%>
<%--<%@include  file="../fragments/js/main.html" %>--%>
<%--<%@include  file="./fragments/js/head.html" %>--%>
<%--<%@include  file="./WEB-INF/fragments/js/main.html" %>--%>

<%--<c:if test="${not empty pageContext.request.getAttribute(\"userName\")}">--%>
<%--    <h1>CRINGE</h1>--%>
<%--    User: <c:out value="${pageContext.request.getAttribute()}" />--%>
<%--</c:if>--%>
<a value="${userName}"> *** </a>

<h1>Hello USER!</h1>
<a href="${pageContext.request.contextPath}/logout">Logout</a>

</body>
</html>