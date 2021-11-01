<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/errorTag.tld" prefix="et" %>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="date.format" var="dataformat"/>
<fmt:message bundle="${bundle}" key="date.locale" var="datelocal"/>
<fmt:message bundle="${bundle}" key="edit.success" var="success"/>
<fmt:message bundle="${bundle}" key="fail" var="fail"/>
<div class="container">
    <div class="row">
        <div class="col-lg-10 mt-5 mb-5">
            <h1><fmt:message key="menu.users"/></h1>
            <table id="posts" class="table table-bordered table-responsive-sm">
                <thead>
                <tr>
                    <th><fmt:message key="users.id"/></th>
                    <th><fmt:message key="users.name"/></th>
                    <th><fmt:message key="users.username"/></th>
                    <th><fmt:message key="users.email"/></th>
                    <th><fmt:message key="users.balance"/></th>
                    <th><fmt:message key="users.enabled"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.balance}"/></td>
                        <td>
                            <label class="switch">
                                <c:if test="${!user.enabled}">
                                    <input class="toggle" id="toggle" type="checkbox" title="${user.id}"
                                           onchange="enable(this)">
                                    <span class="slider round"></span>
                                </c:if>
                                <c:if test="${user.enabled}">
                                    <input class="toggle" id="toggle" type="checkbox" title="${user.id}"
                                           onchange="enable(this)"
                                    checked>
                                    <span class="slider round"></span>
                                </c:if>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation" class="paging">
            <ul class="pagination">
                <c:if test="${pq gt 1}">
                    <li class="${curPage le 1 ? 'page-item disabled' : 'page-item'}">
                        <a class="page-link"
                           href="javascript:paginationNavigation(${curPage - 1})"
                           tabindex="-1"><fmt:message key="pagination.prev"/></a>
                    </li>
                    <c:forEach var="i" begin="1" end="${pq}" step="1">
                        <li class="${curPage == i ? 'page-item disabled' : 'page-item'}">
                            <a class="page-link"
                               href="javascript:paginationNavigation(${i})">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="${curPage ge (pq) ? 'page-item disabled' : 'page-item'}">
                        <a class="page-link"
                           href="javascript:paginationNavigation(${curPage + 1})"
                           tabindex="-1"><fmt:message key="pagination.next"/></a>
                    </li>
                </c:if>
            </ul>
        </nav>

    </div>
</div>
<script type="text/javascript">
    function enable(checkboxElem) {
        const id = checkboxElem.getAttribute("title");
        let success = "${success}";
        let fail = "${fail}";
        let path = "${pageContext.request.contextPath}/admin/users"

        $.ajax({
            type: "POST",
            url: path,
            data: {checked: checkboxElem.checked, id: id},
            error: function () {
                alert(fail);
            },
            success: function () {
                alert(success);
            }

        }).done(
        );
    }
</script>
<style rel="stylesheet">
    <%@include file="/WEB-INF/js/main.css" %>
</style>

</body>
</html>
