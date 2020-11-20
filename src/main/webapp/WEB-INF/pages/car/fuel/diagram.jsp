<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>

<script>
    window.onload = function () {

        var dps = []; // dataPoints
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
        var dataLength = 20; // number of dataPoints visible at any point

        var updateChart = function (count) {

            count = ${fuelList.size()};

            <c:forEach items="${fuelList}" var="fuel">
            yVal = ${fuel.literCost};
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
    <a href="/car/fuel/${car.id}" class="text-info text-right">На страницу авто</a>
</div>
</html>
