///<reference path="../typings/bundle.d.ts"/>

var app = angular.module('mosaModules')

class Droppable {
    static $inject = ['$element']
    constructor(private element: ng.IRootElementService) {
        this.element.on('dragover', (e:any) => {
            e.preventDefault()
            e.dataTransfer.dropEffect = 'none'
        })
    }
}

app.directive('droppable', () => {
    return {
        restrict: 'A',
        scope: false,
        controller: Droppable,
        controllerAs: 'droppable',
        bindToController: true
    }
})

var eventList: Array<{ name: string, fn: (e: DragEvent) => void }> = [
    // {
    //     name: 'dragover', fn: (e: DragEvent) => { e.preventDefault() }
    // },
    { name: 'drop', fn: (e: DragEvent) => { } }]

eventList.forEach((v: { name: string, fn: (e: DragEvent) => void }) => {
    app.directive(v.name, ['$parse', '$rootScope', ($parse, $rootScope) => {
        return {
            restrict: 'A',
            compile: ($element, attr) => {
                var fn = $parse(attr[v.name], null, true)
                return (scope, element) => {
                    element.on(v.name, (event) => {
                        if (scope.$eval(attr['droppable'])) {
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
