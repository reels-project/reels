///<reference path="../typings/bundle.d.ts"/>

import angular = require('angular')
import c3 = require('c3')

var app = angular.module('App')

class Chart {
    private data: any
    private zoomValue: any
    private chart: any

    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {

        var txt = this.getValueTxt().value
        this.data = JSON.parse(txt)
        this.zoomValue = '0'

        this.timeout(() => {
            var zoomHandler = angular.element(this.getZoomHandler())
            zoomHandler.on('mousemove', () => {
                var zoom = angular.element(this.getZoomValueTxt())
                var zoomValue = parseInt((<HTMLInputElement>zoom[0]).value)
                if (!isNaN(zoomValue)) {
                    var tickCnt = d3.select('#' + this.data.chartId).select('svg').select('.c3-axis-x').selectAll('g')[0].length
                    var scale = tickCnt * (zoomValue / 100.0)
                    this.chart.zoom([0, tickCnt - scale])
                }
            })
        })

        var chart = this.getChart()
        chart.id = this.data.chartId

        this.chart = this.generate()
    }

    getValueTxt(): HTMLInputElement {
        return <HTMLInputElement>this.element.parent()[0].getElementsByClassName('text-chart-value')[0]
    }

    getChart() {
        return this.element[0]
    }

    getZoomValueTxt(): HTMLInputElement {
        return <HTMLInputElement>this.element[0].getElementsByClassName('txt-zoom-value')[0]
    }

    getZoomHandler(): HTMLInputElement {
        return <HTMLInputElement>this.element[0].getElementsByClassName('ui-slider-handle')[0]
    }

    getColumns() {
        var d = this.data
        var columns = []
        d.series.forEach((v, i)=> {
            columns.push([v.key].concat(v.data))
        })
        return columns
    }

    getTypes() {
        var d = this.data

        var types = {}
        d.series.forEach((v, i)=> {
            types[v.key] = v.type || d.type
        })
        return types
    }

    generate() {
        var d = this.data
        var conf: any = {
            bindto: '#' + d.chartId,
            data: {
                columns: this.getColumns(),
                types: this.getTypes(),
                order: d.order,
                onclick: (d, element) => {
                    console.log('click')
                    var txt = this.getValueTxt()
                    txt.click()
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
            //			onrendered: angular.bind(this, function(){
            //				console.log('renderd')
            //				console.log(this.scope)
            //				var txt = this.getValueTxt()
            //				txt.click()
            //			})
        }

        if (conf.axis.x.max == null) conf.axis.x.max = undefined
        if (conf.axis.x.min == null) conf.axis.x.min = undefined
        if (conf.axis.y.max == null) conf.axis.y.max = undefined
        if (conf.axis.y.min == null) conf.axis.y.min = undefined
        conf.zoom.extent = [1, 10]

        var chart = c3.generate(conf)
        return chart
    }

}
app.directive('jsfChart', ()=> {
    return {
        restrict: 'C',
        template: '<div id="chart.data.chartId"></div>',
        scope: {},
        controller: Chart,
        controllerAs: 'chart'
    };
});
