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
    <%--    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        div.form
        {
            display: block;
            text-align: center;
        }
        form
        {
            /*display: inline-block;*/
            /*margin-left: auto;*/
            /*margin-right: auto;*/
            text-align: left;
        }

    </style>
    <title>Add item</title>
</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="date.format" var="dataformat"/>
<fmt:message bundle="${bundle}" key="date.locale" var="datelocal"/>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3" id="parent">
            <br/>
            <div class="form">
                <h1><fmt:message key="exhibition"/></h1>
                <form action="${pageContext.request.contextPath}/admin/add-item" method="post">

                    <div class="form-group">

                        <label for="name"><fmt:message key="show.title"/></label>:
                        <input type="text"
                               id="name"
                               name="name"
                               class="form-control"
                               autofocus="autofocus"
                               required/>

                    </div>
                    <div class="form-group">

                        <label for="price"><fmt:message key="show.price"/></label>:
                        <input type="text"
                               id="price"
                               name="price"
                               class="form-control"
                               required/>
                        <et:validator field="price"/>

                    </div>
                    <div class="form-group">

                        <label for="datetimes"><fmt:message key="show.frame"/></label>:
                        <input id="datetimes"
                               name="datetimes"
                               class="form-control " required
                        />
                        <input id="startdatetime"
                               name="startDateTime"
                               class="form-control" hidden required>
                        <input id="enddatetime"
                               name="endDateTime"
                               class="form-control" hidden required>
                    </div>
                    <div class="form-group"
                    >

                        <label for="halls"><fmt:message key="show.halls"/></label>
                        <select id="halls" class="form-control" size="5" name="halls" multiple required>
                            <!--/*@thymesVar id="hall" type="com.example.exhibitions.entity.Hall"*/-->
                            <c:forEach items="${halls}" var="hall">
                                <option value="${hall}">${hall}</option>
                            </c:forEach>
                        </select>
                    </div>
                    </br>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success"><fmt:message key="show.add"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript">
    $(function () {
        let format = "${datelocal}"
        $('input[name="datetimes"]').daterangepicker({
            timePicker: true,
            startDate: moment().startOf('hour'),
            endDate: moment().startOf('hour').add(32, 'hour'),
            locale: {
                format: format
            }
        }, function (start, end, label) {
            document.getElementById("startdatetime").value = start.toLocaleString();
            document.getElementById("enddatetime").value = end.toLocaleString();
        })
    })
</script>

</body>
</html>
