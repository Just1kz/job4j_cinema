<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Payment!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.html"><h4>Главная</h4></a>
            </li>
        </ul>
    </div>
</div>

<%--<div><label></label></div>--%>

<div class="container">
    <div id="aga" class="row pt-3">
        <h3 id="selectedPlace">
        </h3>
    </div>

    <div class="container">
        <div class="row">
            <c:if test="${not empty error}">
                <div style="color:red; font-weight: bold; margin: 30px 0;">
                        ${error}
                </div>
            </c:if>
        </div>
    </div>

    <div class="row">
        <form action="<%=request.getContextPath()%>/payment" method="post">

            <div class="form-group" >
                <input type="hidden" class="form-control" name="id" id="id" placeholder="id">
            </div>
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="ФИО">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" name="phone" id="phone" placeholder="Номер телефона">
            </div>
            <button type="submit" class="btn btn-success" onclick="validate()">Оплатить</button>
<%--            id="paymentZ"--%>
        </form>
    </div>
</div>
</body>

<script>

    $(document).ready(function () {
        let selectedPlace = getPlaceId();
        console.log(selectedPlace.valueOf());
        load(selectedPlace);
        $('#id').val(selectedPlace.valueOf());

        // $('#paymentZ').click(function () {
        //     if (validate()) {
        //         payment();
                // window.location.href = "/cinema/index.html";

        });

    function getPlaceId() {
        return new URLSearchParams(window.location.search).get("place");
    }

    function load(selectedPlace) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/cinema/hall",
            dataType: 'json',
            complete: function ($data) {
                console.log(selectedPlace.valueOf())
                let json = JSON.parse($data.responseText);
                let result = "";
                    result += "<h3> ";
                    for (let j = 0; j < json.length; j++) {
                        let x = selectedPlace;
                        let zxc = json[j]['idH'];
                        console.log(zxc);
                        console.log(selectedPlace);
                        if (zxc==x) {
                            result += "Вы выбрали ряд " + json[j]['rowX']
                                + ", место " + json[j]['columnX'] + " , стоимость: "
                                + json[j]['price'] + " </h3>";
                            console.log(result)
                        }
                    }
                document.getElementById("selectedPlace").innerHTML = result;
            }
        })
    }

    function payment() {
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/cinema/payment',
            data: {id : getPlaceId(), username : $('#username').val(), phone : $('#phone').val()},
            dataType: 'json'
        });
    }

    function validate() {
        let result = true;
        let answer = '';
        let elements = [$("#username"), $("#phone")];
        for (let i = 0; i < elements.length; i++) {
            if (elements[i].val() === '') {
                answer += "Пожалуйста, введите "  + elements[i].attr("placeholder").toLowerCase() + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
</script>

</html>