(function () {
    var app = angular.module('gemStore', ['store-directives']);

    app.controller('StoreController', ['$http', '$scope', function ($http, $scope) {
            var store = this;
            store.products = [];
            $http.get('./store-products.json').success(function (data) {
                store.products = data;
            });
            $scope.setCurrent = function(index) {
                $scope.index = index;
            };
            
        }]);

    app.controller('ReviewController', function () {
        this.review = {};

        this.addReview = function (product) {
            product.reviews.push(this.review);

            this.review = {};
        };
    });
})();