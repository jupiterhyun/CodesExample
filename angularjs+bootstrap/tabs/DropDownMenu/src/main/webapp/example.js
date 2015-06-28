var app = angular.module('plunker', ['ui.bootstrap']);

app.controller('TabsDemoCtrl', function ($scope) {
  $scope.data = [];
  $scope.data.tabs = [
    { title:'Dynamic Title 1', content:'Dynamic content 1', id:1, active:true},
    { title:'Dynamic Title 2', content:'Dynamic content 2', id:2},
    { title:'Dynamic Title 3', content:'Dynamic content 3', id:3},
     { title:'Dynamic Title 4', content:'Dynamic content 4', id:4}
  ];
});

app. directive('sortableTab', function($timeout, $document) {
  return {
    link: function(scope, element, attrs, controller) {
      // Attempt to integrate with ngRepeat
      // https://github.com/angular/angular.js/blob/master/src/ng/directive/ngRepeat.js#L211
      var match = attrs.ngRepeat.match(/^\s*([\s\S]+?)\s+in\s+([\s\S]+?)(?:\s+track\s+by\s+([\s\S]+?))?\s*$/);
      var tabs;

      
      scope.$watch(match[2], function(newTabs) {
        tabs = newTabs;
      });

      var index = scope.$index;
      
      scope.$watch('$index', function(newIndex) {
        index = newIndex;
      });
      
      
      
      
      
      attrs.$set('draggable', true);
      
      // Wrapped in $apply so Angular reacts to changes
      var wrappedListeners = {
        // On item being dragged
        dragstart: function(e) {
          e.dataTransfer.effectAllowed = 'move';
          e.dataTransfer.dropEffect = 'move';
          e.dataTransfer.setData('application/json', index);
          element.addClass('dragging');
        },
        dragend: function(e) {
          //e.stopPropagation();
          element.removeClass('dragging');
        },

        // On item being dragged over / dropped onto
        dragenter: function(e) {
        },
        dragleave: function(e) {
          element.removeClass('hover');
        },
        drop: function(e) {
          e.preventDefault();
          e.stopPropagation();
          var sourceIndex = e.dataTransfer.getData('application/json');
          
          move(sourceIndex, index);
          element.removeClass('hover');
        }
      };
      
      // For performance purposes, do not
      // call $apply for these
      var unwrappedListeners = {
        dragover: function(e) {
          e.preventDefault();
          element.addClass('hover');
        },
        /* Use .hover instead of :hover. :hover doesn't play well with 
           moving DOM from under mouse when hovered */
        mouseenter: function() {
          element.addClass('hover');
        },
        mouseleave: function() {
          element.removeClass('hover');
        }
      };
      
      angular.forEach(wrappedListeners, function(listener, event) {
        element.on(event, wrap(listener));
      });
      
      angular.forEach(unwrappedListeners, function(listener, event) {
        element.on(event, listener);
      });
      
      function wrap(fn) {
        return function(e) {
          scope.$apply(function() {
            fn(e);
          });
        };
      }
      
      function move(fromIndex, toIndex) {
        console.log("-------------")
        console.log(tabs[fromIndex]);
        console.log(tabs[toIndex]);
        tabs[toIndex].id = [tabs[fromIndex].id, tabs[fromIndex].id=tabs[toIndex].id][0]; 
        tabs.splice(toIndex, 0, tabs.splice(fromIndex, 1)[0]);
      };
      
    }
  }
});