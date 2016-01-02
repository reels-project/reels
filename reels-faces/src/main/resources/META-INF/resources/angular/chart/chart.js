var angular = require('angular');
var c3 = require('c3');
var app = angular.module('App');
var Chart = (function () {
    function Chart(scope, element, timeout) {
        var _this = this;
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
        var txt = this.getValueTxt().value;
        this.data = JSON.parse(txt);
        this.zoomValue = '0';
        this.timeout(function () {
            var zoomHandler = angular.element(_this.getZoomHandler());
            zoomHandler.on('mousemove', function () {
                var zoom = angular.element(_this.getZoomValueTxt());
                var zoomValue = parseInt(zoom[0].value);
                if (!isNaN(zoomValue)) {
                    var tickCnt = d3.select('#' + _this.data.chartId).select('svg').select('.c3-axis-x').selectAll('g')[0].length;
                    var scale = tickCnt * (zoomValue / 100.0);
                    _this.chart.zoom([0, tickCnt - scale]);
                }
            });
        });
        var chart = this.getChart();
        chart.id = this.data.chartId;
        this.chart = this.generate();
    }
    Chart.prototype.getValueTxt = function () {
        return this.element.parent()[0].getElementsByClassName('text-chart-value')[0];
    };
    Chart.prototype.getChart = function () {
        return this.element[0];
    };
    Chart.prototype.getZoomValueTxt = function () {
        return this.element[0].getElementsByClassName('txt-zoom-value')[0];
    };
    Chart.prototype.getZoomHandler = function () {
        return this.element[0].getElementsByClassName('ui-slider-handle')[0];
    };
    Chart.prototype.getColumns = function () {
        var d = this.data;
        var columns = [];
        d.series.forEach(function (v, i) {
            columns.push([v.key].concat(v.data));
        });
        return columns;
    };
    Chart.prototype.getTypes = function () {
        var d = this.data;
        var types = {};
        d.series.forEach(function (v, i) {
            types[v.key] = v.type || d.type;
        });
        return types;
    };
    Chart.prototype.generate = function () {
        var _this = this;
        var d = this.data;
        var conf = {
            bindto: '#' + d.chartId,
            data: {
                columns: this.getColumns(),
                types: this.getTypes(),
                order: d.order,
                onclick: function (d, element) {
                    console.log('click');
                    var txt = _this.getValueTxt();
                    txt.click();
                }
            },
            size: d.size,
            padding: d.padding,
            axis: d.axis,
            point: d.point,
            bar: d.bar,
            donut: d.donut,
            pie: d.pie,
            zoom: d.zoom,
        };
        if (conf.axis.x.max == null)
            conf.axis.x.max = undefined;
        if (conf.axis.x.min == null)
            conf.axis.x.min = undefined;
        if (conf.axis.y.max == null)
            conf.axis.y.max = undefined;
        if (conf.axis.y.min == null)
            conf.axis.y.min = undefined;
        conf.zoom.extent = [1, 10];
        var chart = c3.generate(conf);
        return chart;
    };
    Chart.$inject = ['$scope', '$element', '$timeout'];
    return Chart;
})();
app.directive('jsfChart', function () {
    return {
        restrict: 'C',
        template: '<div id="chart.data.chartId"></div>',
        scope: {},
        controller: Chart,
        controllerAs: 'chart'
    };
});
