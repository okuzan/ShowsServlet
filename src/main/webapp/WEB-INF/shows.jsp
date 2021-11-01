<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/errorTag.tld" prefix="et" %>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="date.format" var="dataformat"/>
<fmt:message bundle="${bundle}" key="date.locale" var="datelocal"/>



<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style> h1 {
        text-align: left;
        /*margin-left: 40px;*/
    }

    /*.navbar {*/
    /*    color: #000000;*/
    /*}*/
    /*#header3 {*/
    /*    color: #090101;*/
    /**/
    /*}*/
    </style>
    <title>Shows</title>
</head>
<body>
<%@include file="./fragments/headers/general.jsp" %>
<div id="filterbar" class="collapse">
    <div class="box border-bottom">
        <div class="form-group text-center">
            <div class="btn-group" data-toggle="buttons">
                <button onclick="reset()" class="btn btn-dark"><fmt:message key="reset"/></button>
                <button onclick="refresh()" class="btn btn-dark"><fmt:message key="apply"/></button>
            </div>
        </div>
    </div>

    <div class="box border-bottom">
        <div class="box-label text-uppercase d-flex align-items-center">
            <fmt:message key="show.price"/>
            <button class="btn ml-auto" type="button" data-toggle="collapse" data-target="#price"
                    aria-expanded="false" aria-controls="price"><span class="fas fa-plus"></span></button>
        </div>
        <div class="collapse" id="price">
            <div class="middle">
                <div class="multi-range-slider">
                    <input type="range" id="input-left" min="0" max="${maxPrice}" value="${minPrice}">
                    <input type="range" id="input-right" min="0" max="${maxPrice}" value="${maxPrice}">
                    <div class="slider">
                        <div class="track"></div>
                        <div class="range"></div>
                        <div class="thumb left"></div>
                        <div class="thumb right"></div>
                    </div>
                </div>
            </div>
            <div class="d-flex align-items-center justify-content-between mt-2">
                <div><span id="amount-left" class="font-weight-bold"></span> <fmt:message key="currency"/></div>
                <div><span id="amount-right" class="font-weight-bold"></span> <fmt:message key="currency"/></div>
            </div>
        </div>

    </div>
    <div class="box border-bottom">
        <div class="box-label text-uppercase d-flex align-items-center">
            <fmt:message key="show.frame"/>
            <button class="btn ml-auto" type="button" data-toggle="collapse" data-target="#datetime"
                    aria-expanded="false" aria-controls="price"><span class="fas fa-plus"></span></button>
        </div>

        <div class="collapse" id="datetime">
            <div class="middle">
                <input id="datetimes"
                       name="datetimes"
                       class="form-control"/>
                <input id="startdatetime"
                       name="startdatetime"
                       class="form-control" hidden>
                <!--                       th:field="*{startDateTime}"-->
                <input id="enddatetime"
                       name="enddatetime"
                       class="form-control" hidden>
                <!--                       th:field="*{endDateTime}"-->
            </div>
        </div>
    </div>
    <div class="box">
        <div class="box-label text-uppercase d-flex align-items-center">
            <fmt:message key="show.title"/>
            <button class="btn ml-auto" type="button" data-toggle="collapse" data-target="#namediv"
                    aria-expanded="false" aria-controls="price"><span class="fas fa-plus"></span></button>
        </div>
        <div class="collapse" id="namediv">
            <div class="middle">

                <input type="text"
                       id="name"
                       name="name"
                       class="form-control"
                />
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-lg-10 mt-5 mb-5">
            <ib:infobox cause="edited"/>
            <ib:infobox cause="deleted"/>
            <ib:infobox cause="added"/>
            <ib:infobox cause="failed" danger="true"/>
            <h1><fmt:message key="menu.shows"/></h1>
            <table id="posts" class="table table-bordered table-responsive-sm">
                <thead>
                <tr>
                    <th><fmt:message key="show.title"/></th>
                    <th><fmt:message key="show.start.date"/></th>
                    <th><fmt:message key="show.end.date"/></th>
                    <th><fmt:message key="show.price"/></th>
                    <th><fmt:message key="show.halls"/></th>
                    <c:if test="${sessionScope['role'] == 'USER'}">
                        <th><fmt:message key="buy"/></th>
                    </c:if>
                    <c:if test="${sessionScope['role'] == 'ADMIN'}">
                        <th><fmt:message key="edit"/></th>
                    </c:if>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${shows}" var="show">
                    <tr>
                        <td>
                            <c:out value="${show.name}"/>
                        </td>
                        <td>
                            <fmt:parseDate value="${show.startDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="${dataformat}" value="${ parsedDateTime }"/>
                        </td>
                        <td>
                            <fmt:parseDate value="${show.endDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="${dataformat}" value="${ parsedDateTime }"/>
                        </td>
                        <td>
                            <c:out value="${show.price}"/>
                        </td>
                        <td>
                                <%--                            <c:out value="${show.halls}"/>--%>
                            <c:forEach items="${show.halls}" var="hall" varStatus="status">
                                <c:out value="${hall}"/>
                                <c:if test="${!status.last}">,</c:if>
                            </c:forEach>

                        </td>
                        <c:if test="${sessionScope['role'] == 'USER'}">
                            <td>
                                <button onclick="buy(this)" value="${show.price}" title="${show.id}"
                                        class="btn btn-dark">
                                    <fmt:message key="buy"/></button>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope['role'] == 'ADMIN'}">
                            <td>
                                <button onclick="edit(this)" value="${show.price}" title="${show.id}"
                                        class="btn btn-dark">
                                    <fmt:message key="edit"/></button>
                            </td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
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
</div>
<script type="text/javascript">

    function filterData() {
        $.ajax({
            type: "POST",
            url: "/api/shows/filtered",
            error: function (e) {

                alert('Something went south!');
            },
            // success: function (e) {
            //     alert('good!');
            // }
        }).done(
            function (response) {
                alert(response)
                window.location.href = "/api/shows"
            }
        );

    }
</script>

<script type="text/javascript">
    function buy(btn) {
        // const id = btn.getAttribute("data-id")
        const id = btn.getAttribute("title")
        const price = btn.getAttribute("value")
        alert(price);
        $.ajax({
            type: "POST",
            url: "/buy",
            data: {id: id, price: price},
            error: function (xhr, status, error) {
                // alert('Something went south!');
                // alert(xhr.responseText);
                var err = JSON.parse(xhr.responseText);
                alert(err.message);
                // alert(error);
            },
            success: function (e) {
                alert("success");
            }
        }).done(
            function (response) {
                alert(response)
            }
        );
    }

    function edit(btn) {
        const id = btn.getAttribute("title")
        window.location.href = '/edit-item?id=' + id
    }

    function reset() {
        let max = "${maxPrice}";
        let min = "${minPrice}";
        let format = "${datelocal}";

        document.getElementById("input-left").value = min
        document.getElementById("input-right").value = max;
        // document.getElementById("input-right").value = max;
        document.getElementById("name").value = "";
        console.log(document.getElementById("startdatetime").value)
        document.getElementById("startdatetime").value = "";
        // document.getElementById("datetimes").value = moment();
        $('input[name="datetimes"]').daterangepicker({
            timePicker: true,
            startDate: moment().startOf('hour'),
            endDate: moment().startOf('hour').add(32, 'hour'),
            locale: {
                format: format
            }

        })
        console.log(document.getElementById("startdatetime").value)

        console.log(document.getElementById("enddatetime").value)
        document.getElementById("enddatetime").value = "";
        console.log(document.getElementById("enddatetime").value)

    }

    function refresh() {
        let priceMin = (document.getElementById("input-left").value);
        let priceMax = (document.getElementById("input-right").value);
        let dateStart = (document.getElementById("startdatetime").value);
        let dateEnd = (document.getElementById("enddatetime").value);
        let title = (document.getElementById("name").value);
        console.log(dateEnd);
        console.log(dateStart);
        console.log(priceMin);
        console.log(priceMax);
        console.log(title);

        let url = window.location.href

        // if (priceMin !== "")
        url = URL_add_parameter(url, "priceMin", priceMin)
        // if (priceMax !== "")
        url = URL_add_parameter(url, "priceMax", priceMax)
        // if (title !== "")
        url = URL_add_parameter(url, "title", title)
        // if (dateStart !== "")
        url = URL_add_parameter(url, "start", btoa(dateStart))
        // if (dateEnd !== "")
        url = URL_add_parameter(url, "end", btoa(dateEnd))
        url = URL_add_parameter(url, "page", "")

        window.location.href = url
    }
</script>

<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet'>
<link href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' rel='stylesheet'>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript">
    $(function () {

        $('input[name="datetimes"]').daterangepicker({
            timePicker: true,
            startDate: moment().startOf('hour'),
            endDate: moment().startOf('hour').add(32, 'hour'),
            // locale: {
            //     format: format
            // }
        }, function (start, end, label) {
            document.getElementById("startdatetime").value = start.toLocaleString();
            document.getElementById("enddatetime").value = end.toLocaleString();
        })
    })
</script>

<script type="text/javascript">
    <%@include file="/WEB-INF/js/show.js" %>
</script>
<style rel="stylesheet">
    <%@include file="/WEB-INF/js/shows.css" %>
</style>

<%--<link href="../static/css/shows.css" rel="stylesheet" type="text/css">--%>

</body>
</html>
