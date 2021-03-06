<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ft" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="messages" var="bundle"/>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>

<html>
<head>
    <style>
        h1 {
            text-align: center
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main</title>
</head>
<body>
<%@include file="WEB-INF/fragments/headers/general.jsp" %>
<br/>
<br/>
<ib:infobox cause="access" danger="true"/>
<h1> <fmt:message key="welcome"/></h1>
</body>
</html>
