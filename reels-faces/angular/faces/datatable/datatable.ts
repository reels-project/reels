///<reference path="../typings/bundle.d.ts"/>

import angular = require('angular')

var app = angular.module('App')
class Datatable {
    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfDatatable', () => {
    return {
        restrict: 'E',
        template: `<mosa-datatable><div ng-transclude></div></mosa-datatable>`,
        scope: {},
        transclude: true,
        controller: Datatable
    };
});



class Thead {
    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfThead', () => {
    return {
        restrict: 'E',
        template: '<mosa-thead><div ng-transclude></div></mosa-thead>',
        scope: {},
        transclude: true,
        controller: Thead
    };
});



class Tbody {
    // private data: any
    private sortable: boolean
    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {

        // Dndソート可否
        var fnSortable = this.parse(this.attrs['sortable'])
        this.sortable = fnSortable ? fnSortable(this.scope) : false

        // var txt = this.getValueTxt().value
        // this.data = JSON.parse(txt)
        // console.log(this.data)
    }

    // getValueTxt(): HTMLInputElement {
    //     return <HTMLInputElement>this.element.parent()[0].getElementsByClassName('txt-tbody-value')[0]
    // }

    sorted() {
        console.log('sorted')
        // var jsonStr = JSON.stringify(this.data)
        // var txt = this.getValueTxt()
        // txt.value = jsonStr
        // txt.click()

    }
}
app.directive('jsfTbody', () => {
    return {
        restrict: 'E',
        template: '<mosa-tbody><div mosa-sortable="tbody.sortable" sorted="tbody.sorted()" ng-transclude></div></mosa-tbody>',
        scope: {},
        transclude: true,
        controller: Tbody,
        controllerAs: 'tbody'
    };
});



class Tr {
    static $inject = ['$scope', '$element', '$timeout'];
    constructor(private scope: ng.IScope, private element: ng.IRootElementService, private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTr', () => {
    return {
        restrict: 'E',
        template: '<mosa-tr><div ng-transclude layout="row" class="horizontal-stretch"></div></mosa-tr>',
        scope: {},
        transclude: true,
        controller: Tr
    };
});



class Th {
    private fixed: boolean
    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {

        // 列固定状態
        var fnFixed = this.parse(this.attrs['fixed'])
        this.fixed = fnFixed ? fnFixed(this.scope) : false
    }

    getFixedTxt(): HTMLInputElement {
        return <HTMLInputElement>this.element.parent()[0].getElementsByClassName('txt-th-fixed')[0]
    }
}
app.directive('jsfTh', () => {
    return {
        restrict: 'E',
        template: '<mosa-th fixed="th.fixed"><div ng-transclude></div></mosa-th>',
        scope: {},
        transclude: true,
        controller: Th,
        controllerAs: 'th'
    };
});



class Td {
    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout'];
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('jsfTd', () => {
    return {
        restrict: 'E',
        template: '<mosa-td><div ng-transclude></div></mosa-td>',
        scope: {},
        transclude: true,
        controller: Td
    };
});
