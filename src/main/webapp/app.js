var productapp = angular.module('productapp', ['ngRoute', 'ngAnimate']);

productapp.factory("apiFactory",['$http', function($http){
    var factory = {};
    factory.addproduct =  function (product) {
        return $http({
            method: 'POST',
            url: "http://localhost:8080/productapp/v1/api/addproduct",
            headers: {"Content-Type" : "application/json"},
            data: {
                "id": product.id,
                "name": product.name,
                "category":product.category,
                "description":product.description,
                "seller":product.seller,
                "price":product.price
            }
        });
    };
    factory.browse=function(){
        return $http({
         method:'GET',
         url:"http://localhost:8080/productapp/v1/api/browse",
         headers: {"Content-Type" : "application/json"},
        });
    }

    factory.deleteproduct=function(pid){
        return $http({
            method:'GET',
            url:"http://localhost:8080/productapp/v1/api/delete/"+pid,
            headers: {"Content-Type" : "application/json"},
        });
    }
    return factory;
}]);

productapp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/addproduct', {
        templateUrl: 'addproduct.html',
        controller: 'addproductController'
    }).
      when('/browse', {
        templateUrl: 'browse.html',
        controller: 'browseController'
      }).
      when('/removeproduct', {
              templateUrl: 'removeproduct.html',
              controller: 'deleteproductController'
            }).
      otherwise({
        redirectTo: '/index.html'
      });
}]);


productapp.controller('addproductController', function (apiFactory, $log, $scope){
    $scope.addproduct = function () {
        var product = {
                "id": $scope.id,
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "seller":$scope.seller,
                "price":$scope.price
        };
       apiFactory.addproduct(product).success(function (response){
            $log.log(response);
       }).error();
    }
});

productapp.controller('browseController', function (apiFactory, $log, $scope){
    $scope.browseproduct = function () {
        var product = {
                "id": $scope.id,
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "seller":$scope.seller,
                "price":$scope.price
        };
       apiFactory.browse(product).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.browseproduct();
});

productapp.controller('deleteproductController', function (apiFactory, $log, $scope){
    $scope.deleteproduct = function () {
       apiFactory.deleteproduct($scope.pid).success(function (response){
            $log.log(response);
       }).error();
    }
});