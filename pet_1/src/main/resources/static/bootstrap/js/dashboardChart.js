Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = "#292b2c";

/**
 * @description 차트 색상을 랜덤으로 지정
 * @param {Array} labels - 차트 라벨명 리스트
 * @returns {Array}
 * @author nsy
 */
function randomColor(labels) {
    var colors = [];
    for (let i = 0; i < labels.length; i++) {
        colors.push("#" + Math.round(Math.random() * 0xffffff).toString(16));
    }
    return colors;
}

/**
 * @description Bar 차트를 그려주는 함수
 * @param {Object} ctx - 차트 ID값에 대한 오브젝트
 * @param {String} type - 차트 종류 (bar, pie)
 * @param {Array} labels - 차트 라벨명 리스트
 * @param {Array} data - 차트 데이터 리스트
 * @author nsy
 */
function makeBarChart(ctx, type, labels, data) {
    var myChart = new Chart(ctx, {
        type: type,
        data: {
            labels: labels,
            datasets: [
                {
                    label: "상품별 매출 수량",
                    data: data,
                    backgroundColor: randomColor(labels),
                },
            ],
        },
        options: {
            scales: {
                xAxes: [
                    {
                        gridLines: {
                            display: false,
                        },
                    },
                ],
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true,
                        },
                        gridLines: {
                            display: true,
                        },
                    },
                ],
            },
            legend: {
                display: false,
            },
        },
    });
}

/**
 * @description Pie 차트를 그려주는 함수
 * @param {Object} ctx - 차트 ID값에 대한 오브젝트
 * @param {String} type - 차트 종류 (bar, pie)
 * @param {Array} labels - 차트 라벨명 리스트
 * @param {Array} data - 차트 데이터 리스트
 * @author nsy
 */
function makePieChart(ctx, type, labels, data) {
    var myChart = new Chart(ctx, {
        type: type,
        data: {
            labels: labels,
            datasets: [
                {
                    label: "상품별 매출 수량",
                    data: data,
                    backgroundColor: randomColor(labels),
                },
            ],
        },
        options: {
            legend: {
                display: true,
            },
        },
    });
}

$.ajax({
    type: "GET",
    url: "/DashboardChart",
    dataType: "json",
    success: function (data, status, xhr) {
        console.log(data);

        var labels = [];
        var barData = [];
        var pieData = [];
        var ctx;

        //맵안에 list 였으니 for문으로 돌린다
        $.each(data.list, function (index, data) {
            labels.push(data.p_kind);
            barData.push(data.quantity);
            pieData.push(data.income);
        });

        var newLabels = labels.slice(-5);
        var newBarData = barData.slice(-5);
        var newPieData = pieData.slice(-5);

        ctx = $("#myBarChart");
        makeBarChart(ctx, "bar", newLabels, newBarData);

        ctx = $("#myPieChart");
        makePieChart(ctx, "pie", newLabels, newPieData);
    },
});
