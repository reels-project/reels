///<reference path="../typings/bundle.d.ts"/>

var app = angular.module('mosaModules')

class sortable {
    private DRAG_START: string = 'dragstart'
    private DRAG_OVER: string = 'dragover'
    private DRAG_ENTER: string = 'dragenter'
    private DRAG_LEAVE: string = 'dragleave'
    private DROP: string = 'drop'
    private DRAG_END: string = 'dragend'

    private trList: Array<any> = []
    private srcIndex: number
    private dragStartIndex: number
    // private tbody: ng.IRootElementService
    private ghost: ng.IRootElementService

    private sorted: (fromIndex: { fromIndex: number }, toIndex: { toIndex: number }) => void

    static $inject = ['$scope', '$element', '$attrs', '$parse', '$timeout']
    constructor(
        private scope: ng.IScope,
        private element: ng.IRootElementService,
        private attrs: ng.IAttributes,
        private parse: ng.IParseService,
        private timeout: ng.ITimeoutService) {

        this.setDetaults()
        this.configureWatchers()
        this.bindEvents()
    }

    enabled(): boolean {
        var fnSortable = this.parse(this.attrs['mosaSortable'])
        return fnSortable ? fnSortable(this.scope) : false
    }
    setDetaults() {
        // this.dataSource = this.scope.$eval(this.attrs['sortable'])
        // this.tbody = angular.element(this.element[0].getElementsByTagName('tbody'))
        // this.tbody = this.element
    }

    configureWatchers() {
        if (!this.enabled()) return
        this.element.on('DOMSubtreeModified', (e) => {
            var trList = angular.element(this.element.children())
            trList.off([this.DRAG_START, this.DRAG_OVER, this.DRAG_ENTER, this.DRAG_LEAVE, this.DROP, this.DRAG_END].join(' '))

            trList.attr('draggable', 'true')
            trList.attr('droppable', 'true')
            trList.on(this.DRAG_START, (e) => { this.dragStart(e) })
            trList.on(this.DRAG_OVER, (e) => { this.dragOver(e) })
            trList.on(this.DRAG_ENTER, (e) => { this.dragEnter(e) })
            trList.on(this.DRAG_LEAVE, (e) => { this.dragLeave(e) })
            trList.on(this.DROP, (e) => { this.drop(e) })
            trList.on(this.DRAG_END, (e) => { this.dragEnd(e) })

            this.trList.splice(0, this.trList.length)
            for (var i = 0; i < trList.length; i++) {
                this.trList.push(trList[i])
            }
        })
    }

    bindEvents() {
    }

    ev(e): DragEvent { return <DragEvent>e.originalEvent }

    dragStart(e) {
        var ev = this.ev(e)
        this.callEvent(this.DRAG_START, ev)
        var src = angular.element(ev.srcElement)
        var rect = src[0].getBoundingClientRect()
        this.srcIndex = this.trList.indexOf(src[0])
        this.dragStartIndex = this.srcIndex

        this.timeout(() => { src.css({ opacity: 0 }) })

        var tr = angular.element(src[0].innerHTML)
        tr.css({
            width: rect.width,
            height: rect.height
        })

        this.ghost = angular.element(`<div class="sortable-drag-ghost depth-3"></div>`)
        this.ghost.append(tr)
        this.ghost.css({
            width: rect.width,
            height: rect.height,
            top: src[0].clientTop,
            left: src[0].clientLeft
        })
        this.element.parent().append(this.ghost[0])

        var di = new Image()
        di.hidden = true
        di.src = ""
        di.width = 0
        e.originalEvent.dataTransfer.setDragImage(di, 0, 0);
    }

    dragOver(e) {
        var ev = this.ev(e)
        this.callEvent(this.DRAG_OVER, ev)
        ev.preventDefault()
        if (this.ghost) this.ghost.css({ top: ev.pageY - this.element[0].getBoundingClientRect().top })
    }

    dragEnter(e) {
        var ev = this.ev(e)
        this.callEvent(this.DRAG_ENTER, ev)

        // if (ev.toElement.tagName != 'TD') return

        var tgt = this.getTgtTr(ev.toElement)
        var toIndex = this.trList.indexOf(tgt[0])

        if (toIndex == this.srcIndex) return

        if (toIndex >= this.srcIndex || this.dragStartIndex <= toIndex) {
            // this.swapElement(this.srcIndex, toIndex - 1)
            this.swapElement(this.srcIndex, toIndex)
            this.srcIndex = toIndex
        }
        else if (toIndex <= this.srcIndex || this.dragStartIndex <= toIndex) {
            // this.swapElement(this.srcIndex, toIndex - 1)
            this.swapElement(this.srcIndex, toIndex)
            this.srcIndex = toIndex
        }
    }

    dragLeave(e) {
        this.callEvent(this.DRAG_LEAVE, e)
        var ev = this.ev(e)
    }

    drop(e) {
        this.callEvent(this.DROP, e)
        var ev = this.ev(e)
    }

    dragEnd(e) {
        this.callEvent(this.DRAG_END, e)
        this.callSortedEvent('sorted', { fromIndex: this.dragStartIndex }, { toIndex: this.srcIndex })
        var ev = this.ev(e)
        var src = angular.element(ev.srcElement)

        // this.animate.removeClass(src, 'sortable-drag-src')
        src.css({ opacity: 1 })
        if (this.ghost) this.ghost[0].remove()
    }

    getTr(index: number): HTMLElement {
        return this.element.children()[index]
    }

    getTgtTr(ele: Element): any {
        var e = angular.element(ele)
        if (this.element == e) return null

        var tgt = e.parent()
        return this.trList.indexOf(tgt[0]) > -1 ? tgt : this.getTgtTr(tgt[0])
    }

    callEvent(evenetName: string, e: any) {
        var fn = this.parse.bind(this.attrs[evenetName])
        fn = this.parse(this.attrs[evenetName])
        if (fn) fn(this.scope, { $event: e })
    }

    callSortedEvent(evenetName: string, ...e: any[]) {
        var fn = this.parse.bind(this.attrs[evenetName])
        fn = this.parse(this.attrs[evenetName])
        if (fn) fn(this.scope, e)
    }

    swapElement(srcIndex: number, toIndex: number) {
        if (srcIndex < 0 || toIndex < 0) return
        var from = this.getTr(srcIndex)
        var to = this.getTr(toIndex)
        if (srcIndex < toIndex) {
            from.remove()
            angular.element(to).after(from)
        }
        else {
            to.remove()
            angular.element(from).after(to)
        }
        this.scope.$apply()
    }
}

app.directive('mosaSortable', () => {
    return {
        restrict: 'A',
        scope: false,
        controller: sortable,
        controllerAs: 'sortable',
        bindToController: true
    }
})
