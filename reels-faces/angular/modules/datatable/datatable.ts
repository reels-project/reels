///<reference path="../typings/bundle.d.ts"/>

import angular = require('angular')

var app = angular.module('mosaModules')

class TableRowInfo {
    isSelected: boolean = false
    isHovered: boolean = false
    isChecked: boolean = false
    isExpand: boolean = false
    isEdit: boolean = false
}

class TableColumn {
    key: string
    name: string
    width: string
    isSorted: boolean
    isHovered: boolean = false
    sortMode: SortMode
}

enum SortMode {
    None,
    Asc,
    Desc
}

class FixedFilter {
    constructor() {
        return function(input: Array<any>, fixedList: Array<any>) {
            if (!input) return input
            if (fixedList) {
                return input.filter((v, i) => {
                    return fixedList.every((r) => { return v != r })
                });
            }
            else return input
        }
    }
}

class Datatable {
    // const
    private DEFAULT_WIDTH: string = '100px'
    private ROW_INFO_KEY = 'datatable'

    // element
    private flowBodyContainer: ng.IRootElementService
    private flowBodyTable: ng.IRootElementService
    private fixedContainer: ng.IRootElementService
    private fixedBodyContainer: ng.IRootElementService
    private fixedBodyTable: ng.IRootElementService

    // var

    private selectedRow: any = null

    private checkedAll: boolean = false

    private scrollingY: boolean = false
    private scrollingX: boolean = false

    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {

        this.flowBodyContainer = this.getElementsByClass('datatable-flow-body-container')
        this.flowBodyTable = this.getElementsByClass('datatable-flow-body')
        this.fixedContainer = this.getElementsByClass('datatable-flow')
        this.fixedBodyContainer = this.getElementsByClass('datatable-fixed-body-container')
        this.fixedBodyTable = this.getElementsByClass('datatable-fixed-body')
        this.scope.$broadcast
        this.setDetaults()
        this.configureWatchers()
        // this.bindEvents()
        // // TODO columnsの変更とか監視して幅設定しないとダメな気がする
        // this.setSizeBodyContainer()

        this.scope.$on('fixedColumn', (e, fixed) => {
         console.log('fixedColumn!!')
            console.log(e)
            console.log(fixed)
        })
    }

    getElementsByClass(classVal: string): ng.IRootElementService {
        return angular.element(this.element[0].getElementsByClassName(classVal))
    }

    setDetaults() {
    }

    configureWatchers() {
    }

    bindEvents() {
        var changeScrollingY = () => {
            this.timeout(() => { this.scrollingY = this.flowBodyContainer[0].scrollTop > 0 })
        }
        var changeScrollingX = () => {
            this.timeout(() => { this.scrollingX = this.fixedContainer[0].scrollLeft > 0 })
        }

        this.flowBodyContainer[0].onscroll = (e: Event) => {
            this.fixedBodyContainer[0].scrollTop = this.flowBodyContainer[0].scrollTop
            changeScrollingY()
        }

        this.fixedBodyContainer[0].onwheel = (e: WheelEvent) => {
            var scrollValue = this.flowBodyContainer[0].scrollTop
            var scrollMax = this.flowBodyContainer[0].scrollHeight
            // e.deltaY
            if (scrollMax < scrollValue + e.deltaY) {
                this.flowBodyContainer[0].scrollTop = scrollMax
            }
            else if (scrollValue + e.deltaY < 0) {
                this.flowBodyContainer[0].scrollTop = 0
            }
            else {
                this.flowBodyContainer[0].scrollTop += e.deltaY
                this.fixedBodyContainer[0].scrollTop = scrollValue
            }
            changeScrollingY()
        }

        this.fixedContainer[0].onscroll = (e: Event) => {
            changeScrollingX()
        }
    }

    // 何故かContainerのdivのwidthがスクロール分にFitしてくれないため内Tableのwidthを設定している。
    // バグの素。
    setSizeBodyContainer() {
        this.timeout(() => {
            this.flowBodyContainer.css({ 'min-width': this.flowBodyTable[0].clientWidth })
        })
    }

    // event
    selectRow(d: any) {
        if (this.selectedRow) this.rowInfo(this.selectedRow).isSelected = false
        this.rowInfo(d).isSelected = true
        this.selectedRow = d
    }

    hoverRow(d: any, hover: boolean) {
        this.rowInfo(d).isHovered = hover
    }

    hoverColumn(c: TableColumn, hover: boolean) {
        c.isHovered = hover
    }

    fixedRow(d: any) {
        var rowInfo = this.rowInfo(d)
        rowInfo.isSelected = false
        rowInfo.isHovered = false

        var index = this.fixedRows().indexOf(d)
        if (index == -1) {
            this.fixedRows().push(d)
        }
        else {
            this.fixedRows().splice(index, 1)
        }
    }

    fixedColumn(c: TableColumn) {
        c.isHovered = false
        var index = this.fixedColumns().indexOf(c)
        if (index == -1) {
            this.fixedColumns().push(c)
        }
        else {
            this.fixedColumns().splice(index, 1)
        }
        this.setSizeBodyContainer()
    }

    checkAll() {
        this.rows().forEach((v: any) => { this.rowInfo(v).isChecked = this.checkedAll })
    }

    check() {
        this.checkedAll = this.rows().every((v: any) => { return this.rowInfo(v).isChecked })
    }

    sort(c: TableColumn) {
        c.isSorted = true
        c.sortMode = this.nextSortMode(c.sortMode)
        this.columns().forEach((v: TableColumn) => {
            if (v == c) return
            v.isSorted = false
            v.sortMode = SortMode.None
        })

        var asc = c.sortMode == SortMode.Asc
        this.rows().sort((a: any, b: any) => {
            if (a[c.key] < b[c.key]) return asc ? -1 : 1
            if (a[c.key] > b[c.key]) return asc ? 1 : -1
            return 0;
        })
    }

    nextSortMode(m: SortMode): SortMode {
        if (!m) return SortMode.Asc
        switch (m) {
            case SortMode.Asc:
                return SortMode.Desc
            case SortMode.Desc:
                return SortMode.Asc
        }
    }



    // enabled
    enabledFixedRow(): boolean { return true }
    enabledFixedColumn(): boolean { return true }
    enabledDragdropRow(): boolean { return true }

    // check
    isSelectedRow(d: any): boolean { return this.rowInfo(d).isSelected }

    isHoveredRow(d: any): boolean { return this.rowInfo(d).isHovered }

    isHoveredColumn(c: TableColumn): boolean { return c.isHovered }


    // show
    showRowFixed(d: any): boolean { return this.rowInfo(d).isHovered && this.enabledFixedRow() }
    showColumnFixed(c: TableColumn): boolean { return c.isHovered && this.enabledFixedColumn() }



    //
    rows(): Array<any> { return new Array<any>() }

    columns(): Array<TableColumn> { return new Array<TableColumn>() }

    fixedRows(): Array<any> { return new Array<any>() }

    fixedColumns(): Array<TableColumn> { return new Array<TableColumn>() }

    rowInfo(d: any): TableRowInfo {
        if (!d) return null
        if (!d.hasOwnProperty(this.ROW_INFO_KEY)) d[this.ROW_INFO_KEY] = new TableRowInfo()

        return d[this.ROW_INFO_KEY]
    }

    width(c: TableColumn): string { return c.width || this.DEFAULT_WIDTH }

    sortMode(c: TableColumn) { return c.sortMode || SortMode.None }

    sortModeClass(c: TableColumn): string {
        switch (c.sortMode) {
            case null:
            case SortMode.None:
                return ''
            case SortMode.Asc:
                return 'glyphicon-arrow-up'
            case SortMode.Desc:
                return 'glyphicon-arrow-down'
        }
    }
}

app.filter('datatableFixedFilter', FixedFilter);

app.directive('mosaDatatable', () => {
    var iconClass = "glyphicon"
    var fixedIcon = iconClass + " glyphicon-pushpin"

    var datatableTmplate = `
    <div layout="row"
         flex
         ng-cloak
         class="datatable-container">

         <div>
           <mosa-table>
             <mosa-thead>
             </mosa-thead>
             <mosa-tbody>
             </mosa-tbody>
           </mosa-table>
         </div>

         <mosa-table class="datatable-flow" flex>
           <div ng-transclude layout="column" flex></div>
         </mosa-table>
    </div>`

    return {
        template: datatableTmplate,
        restrict: 'EA',
        scope: {},
        controller: Datatable,
        controllerAs: 'datatable',
        transclude: true,
        bindToController: {
            data: '=',
            settings: '=',
        }
    }
})

class Table {
    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {

        this.scope.$on('sort', () => {
            console.log('sort')
        })


    }
}
app.directive('mosaTable', () => {
    return {
        template: '<div class="mosa-table" ng-transclude layout="column" flex></div>',
        restrict: 'EA',
        scope: {},
        controller: Table,
        controllerAs: 'table',
        transclude: true,
        replace: true,
        bindToController: true
    }
})

class Thead {
    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('mosaThead', () => {
    return {
        template: '<div class="mosa-thead" ng-transclude></div>',
        restrict: 'EA',
        scope: {},
        controller: Thead,
        controllerAs: 'thead',
        transclude: true,
        replace: true,
        bindToController: true
    }
})

class Tbody {
    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('mosaTbody', () => {
    return {
        template: `<div layout="column" flex class="datatable-flow-body-container">
                     <div class="mosa-tbody" ng-transclude></div>
                   </div>`,
        restrict: 'EA',
        scope: {},
        controller: Tbody,
        controllerAs: 'tbody',
        transclude: true,
        replace: true,
        bindToController: true
    }
})

class Tr {
    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('mosaTr', () => {
    return {
        template: '<div class="mosa-tr" ng-transclude></div>',
        restrict: 'EA',
        scope: {},
        controller: Tr,
        controllerAs: 'tr',
        transclude: true,
        replace: true,
        bindToController: true
    }
})


class Th {
    private fixed: boolean

    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {
        // console.log(this.fixed)

        // 初期列固定
        if (this.fixed) {
            this.fixedColumn(this.fixed)
        }
    }

    sort() {
        this.scope.$emit('sort')
    }

    // 列固定状態変更通知
    fixedColumn(fixed: boolean) {
        this.scope.$emit('fixedColumn', fixed)
    }
}
app.directive('mosaTh', () => {
    return {
        template: `<div class="mosa-th" layout="row" ng-click="th.sort()">
                     <div class="mosa-th-content" layout="column" ng-transclude>
                     </div>
                   </div>`,
        restrict: 'EA',
        scope: {},
        controller: Th,
        controllerAs: 'th',
        transclude: true,
        replace: true,
        bindToController: {
            fixed: '='
        }
    }
})


class Td {
    static $inject = ['$scope', '$element', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private timeout: ng.ITimeoutService) {
    }
}
app.directive('mosaTd', () => {
    return {
        template: '<div class="mosa-td" ng-transclude></div>',
        restrict: 'EA',
        scope: {},
        controller: Td,
        controllerAs: 'td',
        transclude: true,
        replace: true,
        bindToController: true
    }
})
