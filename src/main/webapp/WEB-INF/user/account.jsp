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
    <title><fmt:message key="menu.account"/></title>
</head>
<body>
<%@include file="../fragments/headers/general.jsp" %>
<fmt:setBundle basename="messages" var="bundle"/>
<fmt:message bundle="${bundle}" key="replenish.success" var="success"/>
<fmt:message bundle="${bundle}" key="replenish.fail" var="fail"/>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">

            <h1><fmt:message key="menu.account"/></h1>
            <form>
                <div class="form-group">
                    <label for="name"><fmt:message key="users.name"/></label>:
                    <input type="text"
                           id="name"
                           name="name"
                           value="${user.name}"
                           class="form-control"
                           disabled/>
                </div>
                <div class="form-group">
                    <label for="username"><fmt:message key="users.username"/></label>:
                    <input type="text"
                           id="username"
                           value="${user.username}"
                           name="username"
                           class="form-control"
                           disabled/>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="users.email"/></label>:
                    <input type="text"
                           id="email"
                           name="email"
                           value="${user.email}"
                           class="form-control"
                           disabled/>
                </div>
                <div class="form-group">
                    <input class="toggle" id="toggle" checked="${user.enabled}" type="checkbox"
                           onchange="enable(this)" disabled>
                    <label for="toggle"><fmt:message key="users.enabled"/></label>
                </div>

                <div class="form-group">
                    <label for="balanceold"><fmt:message key="users.balance"/></label>:
                    <input type="text"
                           id="balanceold"
                           value="${user.balance}"
                           name="balance"
                           class="form-control"
                           disabled/>
                </div>
            </form>
            </br>
            <input type="text"
                   id="balance"
                   name="balance"
                   class="form-control"
            />
            </br>
            <button onclick="replenish()" class="btn btn-success">
                <fmt:message key="account.button.replenish"/>
            </button>
        </div>
    </div>
</div>
<script type="text/javascript">
    function replenish() {
        let amount = document.getElementById("balance").value;
        let success = "${success}";
        let fail = "${fail}";
        alert(amount);
        $.ajax({
            type: "POST",
            url: "/account",
            data: {balance: amount},
            error: function (e) {
                alert(fail);
            },
            success: function (e) {
                alert(success);
            }
        }).done(
            function (response) {
                // alert(response)
                window.location.reload();
            }
        );

    }
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

</body>
</html>
