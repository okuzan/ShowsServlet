<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<div>
    <header class="p-3 bg-dark text-white" id="header3">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"/>
                    </svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="${pageContext.request.contextPath}/shows" class="nav-link px-2 text-white"><fmt:message key="menu.shows"/></a>
                    </li>
                    <li><a href="/about" class="nav-link px-2 text-white"><fmt:message key="menu.about"/></a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false"><fmt:message key="menu.language"/>
                        </a>
                        <ul class="dropdown-menu" id="locales" aria-labelledby="navbarDropdown">
                            <li><a value="en" id="lang1" class="dropdown-item"><fmt:message key="lang.en"/></a></li>
                            <li><a value="uk" id="lang2" class="dropdown-item"><fmt:message key="lang.uk"/></a></li>
                        </ul>
                    </li>

                </ul>

                <div class="text-end">
                    <button onclick="location.href='/unauth/login'" type="button" class="btn btn-outline-light me-2"
                    ><fmt:message key="login.button"/></button>
                    <button onclick="location.href='/unauth/register'" type="button" class="btn btn-warning"
                    ><fmt:message key="registration.button"/></button>
                </div>
            </div>
        </div>
    </header>
</div>