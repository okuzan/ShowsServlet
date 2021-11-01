<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/errorTag.tld" prefix="et" %>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            </br>
            <ib:infobox cause="registered"/>
            <ib:infobox cause="badData"/>
            </br>
            <h1><fmt:message key="login.title"/></h1>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username"><fmt:message key="login.mail"/></label>:
                    <input type="text"
                           id="username"
                           name="username"
                           class="form-control"
                           autofocus="autofocus"
                    <%--                           placeholder="#{login.username.placeholder}"--%>
                    />
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="login.password"/></label>:
                    <input type="password"
                           id="password"
                           size="100"
                           name="password"
                           class="form-control"
                    <%--                           th:placeholder="#{login.password.placeholder}"--%>
                           placeholder="Password"/>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit"
                                   name="login-submit"
                                   id="login-submit"
                                   class="form-control btn btn-info"
                                   value="<fmt:message key="login.button"/>"/>
                            <%--                            <fmt:message key="login.button"/>--%>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>
                        <span><fmt:message key="login.new_user"/></span>
                        <a href="register"><fmt:message key="login.register"/></a>
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>