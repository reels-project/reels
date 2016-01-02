///<reference path="../typings/bundle.d.ts"/>

import angular = require('angular')

var app = angular.module('App');
class GanttChart {
    private data: any
    private jsonStr: any
    private headers: any
    private headersFormats: any
    private tableColumns: any
    private api: any

    static $inject = ['$scope', '$element'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService) {
        var txt = this.getValueTxt().value
        this.data = JSON.parse(txt)
        this.headers = ['year', 'month', 'day']
        this.headersFormats = { year: 'YYYY', month: 'MM' }
        this.tableColumns = ['model.name']
        
        this.api = (api) => {
            api.tasks.on.change(this.scope, (d) => {
                this.jsonStr = JSON.stringify(this.data)
                this.scope.$apply()

                var txt = this.getValueTxt()
                txt.value = this.jsonStr
                txt.click()
            })
        }
    }

    getValueTxt(): HTMLInputElement {
        return <HTMLInputElement>this.element.parent()[0].getElementsByClassName('txt-gantt-value')[0]
    }
}
app.directive('jsfGanttChart', () => {
    return {
        restrict: 'C',
        template: `<div gantt data="ganttChart.data.rows" headers="ganttChart.headers" headers-formats="ganttChart.headersFormats" current-date="today" style="width: 100%; display:table;" api="ganttChart.api">
           				<gantt-table columns="ganttChart.tableColumns"></gantt-table>
           				<gantt-movable></gantt-movable>
				         </div>`,
        scope: {},
        controller: GanttChart,
        controllerAs: 'ganttChart'
    };
});
