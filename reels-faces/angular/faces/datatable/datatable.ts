///<reference path="../typings/bundle.d.ts"/>

import angular = require('angular')

var app = angular.module('App')
class Datatable {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfDatatable', () => {
    return {
        restrict: 'C',
        template: `<div class="noz-datatable"><div ng-transclude></div></div>`,
        scope: {},
        transclude: true,
        controller: Datatable
    };
});



class Thead {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfThead', () => {
    return {
        restrict: 'C',
        template: '<div class="noz-thead"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Thead
    };
});



class Tbody {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTbody', () => {
    return {
        restrict: 'C',
        template: '<div class="noz-tbody"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Tbody
    };
});



class Tr {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTr', () => {
    return {
        restrict: 'C',
        template: '<div class="noz-tr"><div ng-transclude layout="row"></div></div>',
        scope: {},
        transclude: true,
        controller: Tr
    };
});



class Th {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTh', () => {
    return {
        restrict: 'C',
        template: '<div class="noz-th"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Th
    };
});



class Td {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTd', () => {
    return {
        restrict: 'C',
        template: '<div class="noz-td"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Td
    };
});
