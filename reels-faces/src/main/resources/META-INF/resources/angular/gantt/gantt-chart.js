var angular = require('angular');
var app = angular.module('App');
var GanttChart = (function () {
    function GanttChart(scope, element) {
        var _this = this;
        this.scope = scope;
        this.element = element;
        var txt = this.getValueTxt().value;
        this.data = JSON.parse(txt);
        this.headers = ['year', 'month', 'day'];
        this.headersFormats = { year: 'YYYY', month: 'MM' };
        this.tableColumns = ['model.name'];
        console.log(this.data);
        this.api = function (api) {
            api.tasks.on.change(_this.scope, function (d) {
                _this.jsonStr = JSON.stringify(_this.data);
                _this.scope.$apply();
                var txt = _this.getValueTxt();
                txt.value = _this.jsonStr;
                txt.click();
            });
        };
    }
    GanttChart.prototype.getValueTxt = function () {
        return this.element.parent()[0].getElementsByClassName('txt-gantt-value')[0];
    };
    GanttChart.$inject = ['$scope', '$element'];
    return GanttChart;
})();
app.directive('jsfGanttChart', function () {
    return {
        restrict: 'C',
        template: "<div gantt data=\"ganttChart.data.rows\" headers=\"ganttChart.headers\" headers-formats=\"ganttChart.headersFormats\" current-date=\"today\" style=\"width: 100%; display:table;\" api=\"ganttChart.api\">\n           \t\t\t\t<gantt-table columns=\"ganttChart.tableColumns\"></gantt-table>\n           \t\t\t\t<gantt-movable></gantt-movable>\n\t\t\t\t         </div>",
        scope: {},
        controller: GanttChart,
        controllerAs: 'ganttChart'
    };
});
