///<reference path="../typings/bundle.d.ts"/>

var app = angular.module('mosaModules')

class Draggable {
    // static $inject = []
    constructor() {
    }
}

app.directive('draggable', () => {
    return {
        restrict: 'A',
        scope: false,
        controller: Draggable,
        controllerAs: 'draggable',
        bindToController: true
    }
})

var eventList: Array<{ name: string, fn: (e: DragEvent) => void }> = [
    { name: 'dragstart', fn: (e: DragEvent) => { e.dataTransfer.dropEffect = 'none' } },
    { name: 'drag', fn: (e: DragEvent) => { } },
    { name: 'dragenter', fn: (e: DragEvent) => { } },
    { name: 'dragleave', fn: (e: DragEvent) => { } },
    { name: 'dragend', fn: (e: DragEvent) => { } }]

eventList.forEach((v: { name: string, fn: (e: DragEvent) => void }) => {
    app.directive(v.name, ['$parse', '$rootScope', ($parse, $rootScope) => {
        return {
            restrict: 'A',
            compile: ($element, attr) => {
                var fn = $parse(attr[v.name], null, true)
                return (scope, element) => {
                    element.on(v.name, (event) => {
                        if (scope.$eval(attr['draggable'])) {
                            var callback = () => {
                                v.fn(event)
                                fn(scope, { $event: event });
                            };
                            if ($rootScope.$$phase) {
                                scope.$evalAsync(callback);
                            } else {
                                scope.$apply(callback);
                            }
                        }
                    });
                }
            }
        }
    }])
})
