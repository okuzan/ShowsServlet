<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@include  file="../js/head.html" %>
<%@include  file="../js/main.html" %>
<c:choose>
    <c:when test="${not empty applicationScope['username']}">
        <%@include file="auth.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="unauth.jsp" %>
    </c:otherwise>
</c:choose>
