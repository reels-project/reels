var angular = require('angular');
var app = angular.module('App');
var Datatable = (function () {
    function Datatable(scope, element, attrs, parse, timeout) {
        this.scope = scope;
        this.element = element;
        this.attrs = attrs;
        this.parse = parse;
        this.timeout = timeout;
    }
    Datatable.$inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    return Datatable;
})();
app.directive('jsfDatatable', function () {
    return {
        restrict: 'E',
        template: "<mosa-datatable><div ng-transclude></div></mosa-datatable>",
        scope: {},
        transclude: true,
        controller: Datatable
    };
});
var Thead = (function () {
    function Thead(scope, element, attrs, parse, timeout) {
        this.scope = scope;
        this.element = element;
        this.attrs = attrs;
        this.parse = parse;
        this.timeout = timeout;
    }
    Thead.$inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    return Thead;
})();
app.directive('jsfThead', function () {
    return {
        restrict: 'E',
        template: '<mosa-thead><div ng-transclude></div></mosa-thead>',
        scope: {},
        transclude: true,
        controller: Thead
    };
});
var Tbody = (function () {
    function Tbody(scope, element, attrs, parse, timeout) {
        this.scope = scope;
        this.element = element;
        this.attrs = attrs;
        this.parse = parse;
        this.timeout = timeout;
        var fnSortable = this.parse(this.attrs['sortable']);
        this.sortable = fnSortable ? fnSortable(this.scope) : false;
    }
    Tbody.prototype.sorted = function () {
        console.log('sorted');
    };
    Tbody.$inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    return Tbody;
})();
app.directive('jsfTbody', function () {
    return {
        restrict: 'E',
        template: '<mosa-tbody><div mosa-sortable="tbody.sortable" sorted="tbody.sorted()" ng-transclude></div></mosa-tbody>',
        scope: {},
        transclude: true,
        controller: Tbody,
        controllerAs: 'tbody'
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
        restrict: 'E',
        template: '<mosa-tr><div ng-transclude layout="row" class="horizontal-stretch"></div></mosa-tr>',
        scope: {},
        transclude: true,
        controller: Tr
    };
});
var Th = (function () {
    function Th(scope, element, attrs, parse, timeout) {
        this.scope = scope;
        this.element = element;
        this.attrs = attrs;
        this.parse = parse;
        this.timeout = timeout;
        var fnFixed = this.parse(this.attrs['fixed']);
        this.fixed = fnFixed ? fnFixed(this.scope) : false;
    }
    Th.prototype.getFixedTxt = function () {
        return this.element.parent()[0].getElementsByClassName('txt-th-fixed')[0];
    };
    Th.$inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    return Th;
})();
app.directive('jsfTh', function () {
    return {
        restrict: 'E',
        template: '<mosa-th fixed="th.fixed"><div ng-transclude></div></mosa-th>',
        scope: {},
        transclude: true,
        controller: Th,
        controllerAs: 'th'
    };
});
var Td = (function () {
    function Td(scope, element, attrs, parse, timeout) {
        this.scope = scope;
        this.element = element;
        this.attrs = attrs;
        this.parse = parse;
        this.timeout = timeout;
    }
    Td.$inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    return Td;
})();
app.directive('jsfTd', function () {
    return {
        restrict: 'E',
        template: '<mosa-td><div ng-transclude></div></mosa-td>',
        scope: {},
        transclude: true,
        controller: Td
    };
});
