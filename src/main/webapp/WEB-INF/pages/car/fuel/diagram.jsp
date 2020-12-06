<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>

<script>
    window.onload = function () {

        var dps = [];
        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "График цен на топливо"
            },
            axisY: {
                includeZero: false
            },
            data: [{
                type: "line",
                dataPoints: dps
            }]
        });

        var xVal = 0;
        var yVal = 0;
        var dataLength = 20;

        var updateChart = function (count) {

            count = ${fuelList.size()};

            <c:forEach items="${fuelList}" var="fuel">
            yVal = ${fuel.literCost};
            xVal = ${fuel.dateFuel.day};
                dps.push({
                    x: xVal,
                    y: yVal
                });
            xVal++;
            </c:forEach>

            if (dps.length > dataLength) {
                dps.shift();
            }

            chart.render();
        };

        updateChart(dataLength);


    }
</script>
<body>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
<div id="register-link">
    <a href="/car/fuel/${car.id}" class="text-info text-right">Вернуться обратно</a>
</div>
</html>
