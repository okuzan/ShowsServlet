<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/errorTag.tld" prefix="et" %>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>

<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript">
<%--        <%@include file="../static/js/jquery.validate.js" %>--%>
    </script>
    <style>
        .error-message {
            color: red;
        }
    </style>

</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<neone id="neone">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
            </br>
                <h1><fmt:message key="registration.title"/></h1>
                <form id="regForm" action="${pageContext.request.contextPath}/register" method="post">

                    <div class="form-group">
                        <label for="name" class="control-label">
                            <fmt:message key="registration.name"/>
                        </label>
                        <input id="name"
                               name="name"
                               class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="username" class="control-label"><fmt:message key="registration.username"/></label>
                        <input id="username"
                               name="username"
                               minlength="4"
                               class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label"><fmt:message key="registration.email"/></label>
                        <input id="email"
                               name="email"
                               type="email"
                               class="form-control" required/>
                        <et:validator field="email"/>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label"><fmt:message key="registration.password"/></label>
                        <input id="password"
                               name="password"
                               class="form-control"
                               type="password" required/>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="control-label">
                            <fmt:message key="registration.confirm_password"/>
                        </label>
                        <input id="confirmPassword"
                               name="confirmPassword"
                               class="form-control"
                               type="password" required/>
                        <et:validator field="confirmPassword"/>
                    </div>
                    <div class="form-group">
                        <input id="terms"
                               type="checkbox" required/>
                        <label>
                            <span><fmt:message key="registration.terms.agree"/></span>
                            <a href="terms"><fmt:message key="registration.terms.href"/></a>
                        </label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">
                            <fmt:message key="registration.button"/>
                        </button>
                        <span><fmt:message key="registration.already"/> </span>
                        <a href="login"><fmt:message key="registration.button.login"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</neone>

</body>
</html>