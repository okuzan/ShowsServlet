<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<div>
    <header class="p-3 mb-3 border-bottom">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"/>
                    </svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="${pageContext.request.contextPath}/all-shows" class="nav-link px-2 text-primary"><fmt:message key="menu.shows"/></a>
                    <c:if test="${sessionScope['role'] == 'ADMIN'}">
                        <li><a href="/api/users" class="nav-link px-2 link-dark"><fmt:message key="menu.users"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['role'] == 'ADMIN'}">
                        <li><a href="/add-item" class="nav-link px-2 link-dark"><fmt:message key="menu.add"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope['role'] == 'USER'}">
                        <li><a href="/tickets" class="nav-link px-2 link-dark"><fmt:message key="menu.tickets"/></a>
                        </li>
                    </c:if>
                    </li>
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

                <div class="dropdown text-end">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="<c:url value="/account"/>"><fmt:message
                                key="menu.account"/></a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" onclick="logout_handler()"><fmt:message
                                key="menu.profile.sign-out"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
</div>