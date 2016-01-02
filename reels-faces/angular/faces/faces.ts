///<reference path='./typings/bundle.d.ts'/>

import angular = require('angular')
import 'moment'
import 'angular-moment'
import 'angular-gantt'
import 'angular-gantt/assets/angular-gantt-plugins'
import 'd3'
import 'c3'

var app = angular.module('App', [
    'mosaModules',
    'angularMoment',
    'gantt',
    'gantt.tooltips',
    'gantt.table',
    'gantt.resizeSensor',
    'gantt.movable'
])

class AppCtrl {
    static $inject = ['$scope', '$element', '$compile', '$timeout']
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private compile: ng.ICompileService, private timeout: ng.ITimeoutService) {
        this.scope['compileElement'] = angular.bind(this, this.compileElement)
    }
    compileElement(id) {
        if (id == undefined) return
        var tag = document.getElementById(id)
        this.scope.$apply(() => {
            var compiled = this.compile(angular.element(tag)[0])
            compiled(this.scope)
            console.log('id:' + id + ', directive compiled!')
        })
    }
}
app.controller('AppCtrl', AppCtrl)

// faces directives
import './chart/chart'
import './datatable/datatable'
import './gantt/gantt-chart'
