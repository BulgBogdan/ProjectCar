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
                text: "Dynamic Price Liter Fuel"
            },
            axisY: {
                includeZero: true
            },
            data: [{
                type: "line",
                dataPoints: dps
            }]
        });

        var xVal = 0;
        var yVal = (${maxPriceLiterFuel}) * 2;
        var updateInterval = 1000;
        var dataLength = 20; // number of dataPoints visible at any point

        var updateChart = function (${fuelList}) {
            var listFuelLiterPrice = ${fuelList};
            for (var j = 0; j < listFuelLiterPrice.size; j++) {
                dps.push({
                    x: xVal,
                    y: yVal
                });
            }

            if (dps.length > dataLength) {
                dps.shift();
            }

            chart.render();
        };

        updateChart(dataLength);
        setInterval(function () {
            updateChart()
        }, updateInterval);

    }
</script>
<body>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>

</html>
