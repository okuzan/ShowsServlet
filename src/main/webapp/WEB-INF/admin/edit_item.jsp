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

    <title>Edit item</title>
</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="date.format" var="dataformat"/>
<fmt:message bundle="${bundle}" key="date.locale" var="datelocal"/>
<fmt:message bundle="${bundle}" key="delete.success" var="delete"/>

<div class="container">
    <div class="row">
        <br class="col-md-6 col-md-offset-3">

        <h1><fmt:message key="exhibition"/></h1>
        <form action="${pageContext.request.contextPath}/admin/edit-item?id=${show.id}" method="post">

            <div class="form-group">

                <label for="name"><fmt:message key="show.title"/></label>:
                <input type="text"
                       id="name"
                       name="name"
                       value="${show.name}"
                       class="form-control"
                       autofocus="autofocus"
                       required/>

            </div>
            <div class="form-group">

                <label for="price"><fmt:message key="show.price"/></label>:
                <input type="text"
                       id="price"
                       name="price"
                       value="${show.price}"
                       class="form-control"
                       required/>
                <et:validator field="price"/>

            </div>
            <div class="form-group">

                <label for="datetimes"><fmt:message key="show.frame"/></label>:
                <input id="datetimes"
                       name="datetimes"
                       class="form-control "
                />
                <input id="startDateTime"
                       name="startDateTime"
                       value="${show.startStr}"
                       class="form-control" hidden>
                <input id="endDateTime"
                       name="endDateTime"
                       value="${show.endStr}"
                       class="form-control" hidden>
            </div>
            <div class="form-group"
            >

                <label for="halls"><fmt:message key="show.halls"/></label>
                <select id="halls" class="form-control" size="5" name="halls" multiple required>
                    <!--/*@thymesVar id="hall" type="com.example.exhibitions.entity.Hall"*/-->
                    <c:forEach items="${halls}" var="hall">
                        <c:set var="contains" value="false"/>
                        <c:forEach var="realHole" items="${show.halls}">
                            <c:if test="${hall eq realHole}">
                                <c:set var="contains" value="true"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${contains}">
                            <option value="${hall}" selected>${hall}</option>
                        </c:if>
                        <c:if test="${!contains}">
                            <option value="${hall}">${hall}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            </br>
            <div class="form-group">
                <button type="submit" class="btn btn-success"><fmt:message key="edit"/></button>
            </div>
        </form>
        </br>
        <div class="form-group">
            <button onclick="deleteItem()" class="btn btn-danger">
                <fmt:message key="delete"/>
            </button>
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
    function deleteItem() {
        let id = "${show.id}"
        let deleteMsg = "${delete}"
        let path = "${pageContext.request.contextPath}/admin/edit-item?id=" + id;

        alert(id)
        $.ajax({
                type: "DELETE",
                url: path,
                data: {id: id},
                error: function (e) {
                    alert('Something went south!');
                },
            }
        ).done(
            function (response) {
                alert(response)
                window.location.href = "/shows"
            }
        );
    }

    $(function () {
        let startDate = "${show.startDate}"
        let endDate = "${show.endDate}"
        let format = "${datelocal}"
        document.getElementById("startDateTime").value = moment(Date.parse(startDate)).toLocaleString();
        document.getElementById("endDateTime").value = moment(Date.parse(endDate)).toLocaleString();
        // $('input[name="datetimes"]').setDefaults($('input[name="datetimes"]').regional['us']);

        $('input[name="datetimes"]').daterangepicker({
            timePicker: true,
            startDate: moment(Date.parse(startDate)),
            endDate: moment(Date.parse(endDate)),
            // format: 'YYYY-MM-DD'
            locale: {
                format: format
            }
        }, function (start, end, label) {
            document.getElementById("startDateTime").value = start.toLocaleString();
            document.getElementById("endDateTime").value = end.toLocaleString();
        })
    })
</script>

</body>
</html>
