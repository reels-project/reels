var angular = require('angular');
var app = angular.module('App');
var Datatable = (function () {
    function Datatable(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Datatable.$inject = ['$scope', '$element', '$timeout'];
    return Datatable;
})();
app.directive('jsfDatatable', function () {
    return {
        restrict: 'C',
        template: "<div class=\"noz-datatable\"><div ng-transclude></div></div>",
        scope: {},
        transclude: true,
        controller: Datatable
    };
});
var Thead = (function () {
    function Thead(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Thead.$inject = ['$scope', '$element', '$timeout'];
    return Thead;
})();
app.directive('jsfThead', function () {
    return {
        restrict: 'C',
        template: '<div class="noz-thead"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Thead
    };
});
var Tbody = (function () {
    function Tbody(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Tbody.$inject = ['$scope', '$element', '$timeout'];
    return Tbody;
})();
app.directive('jsfTbody', function () {
    return {
        restrict: 'C',
        template: '<div class="noz-tbody"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Tbody
    };
});
var Tr = (function () {
    function Tr(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Tr.$inject = ['$scope', '$element', '$timeout'];
    return Tr;
})();
app.directive('jsfTr', function () {
    return {
        restrict: 'C',
        template: '<div class="noz-tr"><div ng-transclude layout="row"></div></div>',
        scope: {},
        transclude: true,
        controller: Tr
    };
});
var Th = (function () {
    function Th(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Th.$inject = ['$scope', '$element', '$timeout'];
    return Th;
})();
app.directive('jsfTh', function () {
    return {
        restrict: 'C',
        template: '<div class="noz-th"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Th
    };
});
var Td = (function () {
    function Td(scope, element, timeout) {
        this.scope = scope;
        this.element = element;
        this.timeout = timeout;
    }
    Td.$inject = ['$scope', '$element', '$timeout'];
    return Td;
})();
app.directive('jsfTd', function () {
    return {
        restrict: 'C',
        template: '<div class="noz-td"><div ng-transclude></div></div>',
        scope: {},
        transclude: true,
        controller: Td
    };
});
