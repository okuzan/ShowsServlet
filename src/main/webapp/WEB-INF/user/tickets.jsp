<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    <link rel="stylesheet" type="text/css" th:href="@{/css/main.css}"/>--%>
    <%--    <link rel="stylesheet" type="text/css" th:href="@{/css/shows.css}"/>--%>
    <%--    <script type="text/javascript" th:src="@{/js/show.js}"></script>--%>
    <%--    <script type='text/javascript'--%>
    <%--            src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>--%>
    <%--    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet'>--%>
    <%--    <link href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' rel='stylesheet'>--%>
    <%--    <!--    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>-->--%>
    <%--    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>--%>
    <%--    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>--%>
    <%--    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>--%>
    <%--    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>--%>

    <title><fmt:message key="menu.tickets"/></title>
</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="replenish.success" var="success"/>
<fmt:message bundle="${bundle}" key="replenish.fail" var="fail"/>
<fmt:message bundle="${bundle}" key="date.format" var="dataformat"/>

<div class="container">
    <div class="row">
        <div class="col-lg-10 mt-5 mb-5">
            <h1><fmt:message key="menu.tickets"/></h1>
        </br>
            <table id="posts" class="table table-bordered table-responsive-sm">
                <thead>
                <tr>
                    <th><fmt:message key="users.id"/></
                    </th>
                    <th><fmt:message key="show.title"/></
                    </th>
                    <th><fmt:message key="show.start.date"/></
                    </th>
                    <th><fmt:message key="show.end.date"/></
                    </th>
                    <th><fmt:message key="show.halls"/></
                    </th>
                    <th><fmt:message key="show.price"/></
                    </th>
                    <th><fmt:message key="ticket.stamp"/></
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td>
                            <c:out value="${ticket.id}"/>
                        </td>
                        <td>
                            <c:out value="${ticket.show.name}"/>
                        </td>
                        <td>
                            <fmt:parseDate value="${ticket.show.startDate}" pattern="yyyy-MM-dd'T'HH:mm"
                                           var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="${dataformat}" value="${ parsedDateTime }"/>
                        </td>
                        <td>
                            <fmt:parseDate value="${ticket.show.endDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="${dataformat}" value="${ parsedDateTime }"/>
                        </td>
                        <td>
                                <%--                            <c:out value="${show.halls}"/>--%>
                            <c:forEach items="${ticket.show.halls}" var="hall" varStatus="status">
                                <c:out value="${hall}"/>
                                <c:if test="${!status.last}">,</c:if>
                            </c:forEach>

                        </td>
                        <td>
                            <c:out value="${ticket.price}"/>
                        </td>
                        <td>
                            <fmt:parseDate value="${ticket.stamp}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="${dataformat}" value="${ parsedDateTime }"/>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>
