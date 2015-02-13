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
      when('/order', {
              templateUrl: 'addorder.html',
              controller: 'orderController'
      }).
      when('/vieworders', {
              templateUrl: 'vieworder.html',
              controller: 'vieworderController'
            }).

      when('/viewsold', {
              templateUrl: 'sellersold.html',
              controller: 'sellersoldController'
            }).
      when('/removeproduct', {
              templateUrl: 'removeproduct.html',
              controller: 'removeController'
            }).
      otherwise({
        redirectTo: '/buyerproducts.html'
      });
}]);