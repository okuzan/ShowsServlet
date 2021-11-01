<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/infoTag.tld" prefix="ib" %>

<html>
<head>
    <title>Login</title>
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">--%>

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
        <div id="price">
            <div class="middle">
                <div class="multi-range-slider">
                    <input type="range" id="input-left" min="0" max="100" value="90">
                    <input type="range" id="input-right" min="0" max="100" value="10">
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

        <div  id="datetime">
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
        <div id="namediv">
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
<script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet'>
<link href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' rel='stylesheet'>
<%--<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>--%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script th:type="text/javascript">
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

</body>
</html>