
var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function ($scope, $http) {
  
    $http.get('phone.json').success(function(data) {
  
//      $scope.phones = data.splice(0, 5);
        $scope.phones = data;
//        $scope.testphones = data;

  });

  $scope.orderProp = 'age';
});






