var angular = require('angular');
require('moment');
require('angular-moment');
require('angular-gantt');
require('angular-gantt/assets/angular-gantt-plugins');
require('d3');
require('c3');
var app = angular.module('App', [
    'mosaModules',
    'angularMoment',
    'gantt',
    'gantt.tooltips',
    'gantt.table',
    'gantt.resizeSensor',
    'gantt.movable'
]);
var AppCtrl = (function () {
    function AppCtrl(scope, element, compile, timeout) {
        this.scope = scope;
        this.element = element;
        this.compile = compile;
        this.timeout = timeout;
        this.scope['compileElement'] = angular.bind(this, this.compileElement);
    }
    AppCtrl.prototype.compileElement = function (id) {
        var _this = this;
        if (id == undefined)
            return;
        var tag = document.getElementById(id);
        this.scope.$apply(function () {
            var compiled = _this.compile(angular.element(tag)[0]);
            compiled(_this.scope);
            console.log('id:' + id + ', directive compiled!');
        });
    };
    AppCtrl.$inject = ['$scope', '$element', '$compile', '$timeout'];
    return AppCtrl;
})();
app.controller('AppCtrl', AppCtrl);
require('./chart/chart');
require('./datatable/datatable');
require('./gantt/gantt-chart');
