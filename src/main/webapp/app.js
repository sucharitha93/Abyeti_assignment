var productapp = angular.module('productapp', ['ngRoute', 'ngAnimate','ngCookies']);

productapp.factory("apiFactory",['$http', function($http){
    var factory = {};

    factory.loggingseller=function(data)
            {
                return $http({
                    method:'GET',
                    url:"http://localhost:8080/productapp/sellerproducts.html",
                    headers: {"Content-Type" : "application/json"},
                    data:{
                            "username":data.username,
                            "id":data.id,
                         }
                });
            }

    factory.loggingbuyer=function(data)
                {
                    return $http({
                        method:'GET',
                        url:"http://localhost:8080/productapp/buyerscreen.html",
                        headers: {"Content-Type" : "application/json"},
                        data:{
                                "username":data.username,
                                "id":data.id,
                             }
                    });
                }

    factory.sellerlogin=function(data)
        {
            return $http({
                method:'POST',
                url:"http://localhost:8080/productapp/v1/api/loginseller",
                headers: {"Content-Type" : "application/json"},
                data:{
                        "username":data.username,
                        "password":data.password,
                     }
            });
        }
    factory.customerlogin=function(data)
        {
            return $http({
                method:'POST',
                url:"http://localhost:8080/productapp/v1/api/logincustomer",
                headers: {"Content-Type" : "application/json"},
                data:{
                        "username":data.username,
                        "password":data.password,
                     }
            });
        }

    factory.sellerunsold=function(userID)
    {
                return $http({
                 method:'POST',
                 url:"http://localhost:8080/productapp/v1/api/sellerunsold",
                 headers: {"Content-Type" : "application/json"},
                 data: {
                      "message" : userID
                       }
                });
    }
    factory.sellersold=function(userID)
    {
                return $http({
                 method:'POST',
                 url:"http://localhost:8080/productapp/v1/api/sellersold",
                 headers: {"Content-Type" : "application/json"},
                 data: {
                       "message" : userID
                       }
                });
    }

    factory.vieworder=function(userID)
    {
            return $http({
                method:'POST',
                url:"http://localhost:8080/productapp/v1/api/vieworder",
                headers: {"Content-Type" : "application/json"},
                data: {
                    "message" : userID
                }
            });

    }
    factory.addorder=function(data)
    {
        return $http({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/addorder",
            headers: {"Content-Type" : "application/json"},
            data:{
                "pid":data.pid,
                "cid":data.cid,
                 }
            });
    }
    factory.addproduct=function(data)
    {
        return $http
        ({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/addproduct",
            headers: {"Content-Type" : "application/json"},
            data:{
                    "name":data.name,
                    "category":data.category,
                    "description":data.description,
                    "price":data.price,
                    "sellerid":data.sellerid,
                 }
        });
    }
    factory.browse=function()
    {
        return $http
        ({
            method:'GET',
            url:"http://localhost:8080/productapp/v1/api/browse",
            headers: {"Content-Type" : "application/json"},
        });
    }

    factory.removeproduct=function(data)
    {
        return $http({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/removeproduct",
            headers: {"Content-Type" : "application/json"},
            data: {
                    "pid":data.pid,
                  }
            });
    }
    factory.bregister=function(data)
    {
        return $http({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/bregister",
            headers: {"Content-Type" : "application/json"},
            data: {
             "name": data.name,
             "username":data.username,
             "password":data.password,
             "address":data.address,
                  }
            });
    }
    factory.sregister=function(data)
    {
        return $http({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/sregister",
            headers: {"Content-Type" : "application/json"},
            data:{
                        "username":data.username,
                        "password":data.password,
                        "name": data.name,
                        "location":data.location,
                    }
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
      when('/viewunsold', {
                    templateUrl: 'sellerunsold.html',
                    controller: 'sellerunsoldController'
            }).
      when('/removeproduct', {
              templateUrl: 'removeproduct.html',
              controller: 'removeController'
            }).
      when('/seller', {
                          templateUrl: 'sellerlogin.html',
                          controller: 'sellerloginController'
            }).
      when('/customer', {
                    templateUrl: 'customerlogin.html',
                    controller: 'customerloginController'
            }).
      otherwise({
        redirectTo: '/index.html'
      });
}]);

productapp.controller('registerbuyerController',function(apiFactory,$log,$scope){
    $scope.bregister=function(){
    var data={
        "name": $scope.name,
        "username":$scope.username,
        "password":$scope.password,
        "address":$scope.address
        };

    apiFactory.bregister(data).success(function (response){
        $log.log(response);
        if(response.STATUS == "SUCCEEDED"){
                                    sweetAlert({
                                        type:"success",
                                        title: "Success!",
                                        text: "Customer Added Successfully"
                                    });
                                }
                                else {
                                    sweetAlert({
                                        type: "error",
                                        title: "Oops..!",
                                        text: "Something went wrong."
                                    });
                                }
        }).error();
    }
});


productapp.controller('removeController',function(apiFactory,$log,$scope){
    $scope.removeproduct=function(){
    var data={
        "pid": $scope.pid,
             };

    apiFactory.removeproduct(data).success(function (response){
        $log.log(response);
        if(response.STATUS == "SUCCEEDED"){
                                            sweetAlert({
                                                type:"success",
                                                title: "OK",
                                                text: "Product Removed Successfully"
                                            });
                                        }
                                        else {
                                            sweetAlert({
                                                type: "error",
                                                title: "Oops..!",
                                                text: "Something went wrong."
                                            });
                                        }
        }).error();
    }
});


productapp.controller('registersellerController',function(apiFactory,$log,$scope){
    $scope.sregister=function(){
    var data={
        "username":$scope.username,
        "password":$scope.password,
        "name": $scope.name,
        "location":$scope.location,
    };
    $log.log(data);
    apiFactory.sregister(data).success(function (response){
        $log.log(response);
        if(response.STATUS == "SUCCEEDED"){
                                    sweetAlert({
                                        type:"success",
                                        title: "Success!",
                                        text: "Seller Added Successfully"
                                    });
                                }
                                else {
                                    sweetAlert({
                                        type: "error",
                                        title: "Oops..!",
                                        text: "Something went wrong."
                                    });
                                }
        }).error();
    }
});

productapp.controller('browseController', function (apiFactory, $log, $scope){
    $scope.browse = function () {
        var product = {
                "id": $scope.id,
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "sellerid":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.browse(product).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.browse();
});


productapp.controller('orderController',function(apiFactory,$log,$scope,$cookieStore){
    $scope.userID=$cookieStore.get('userID');
    $scope.addorder=function(){
    var data={
        "pid":$scope.pid,
        "cid":$cookieStore.get('userID'),
    };
    $log.log(data);
        apiFactory.addorder(data).success(function (response){
            $log.log(response);
            if(response.STATUS == "SUCCEEDED"){
                                        sweetAlert({
                                            type:"success",
                                            title: "OK!",
                                            text: "Order Added Successfully"
                                        });
                                    }
                                    else {
                                        sweetAlert({
                                            type: "error",
                                            title: "Oops..!",
                                            text: "Something went wrong."
                                        });
                                    }
            }).error();
    }
});



productapp.controller('vieworderController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.vieworder = function () {
    var userID=$cookieStore.get('userID');
        var product = {
                "oid": $scope.id,
                "pname": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "sid":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.vieworder(userID).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.vieworder();
});

productapp.controller('sellersoldController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.sellersold = function () {
    var userID=$cookieStore.get('userID');
        var product = {
                "pid": $scope.id,
                "pname": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "id":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.sellersold(userID).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.sellersold();
});

productapp.controller('sellerunsoldController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.sellerunsold = function () {
    var userID=$cookieStore.get('userID');
        var product = {
                "id": $scope.id,
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "sellerid":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.sellerunsold(userID).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.sellerunsold();
});

productapp.controller('addproductController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.addproduct = function () {
        var product = {
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "price":$scope.price,
                "sellerid":$cookieStore.get('userID'),
        };

       apiFactory.addproduct(product).success(function (response){

            $log.log(response);
            $scope.products = response;

            if(response.STATUS == "SUCCEEDED"){
                            sweetAlert({
                                type:"success",
                                title: "Added Sucessfully!",
                                text: "Product Added Successfully"
                            });
                        }
                        else {
                            sweetAlert({
                                type: "error",
                                title: "Oops..!",
                                text: "Something went wrong."
                            });
                        }
       }).error();
    }
});




productapp.controller('customerloginController', function (apiFactory, $rootScope, $log, $scope,$cookieStore,$location){
    $scope.customerlogin = function(){
         var data = {
                        "username": $scope.username,
                        "password":$scope.password,
                };

       apiFactory.customerlogin(data).success(function (response){

            $log.log(response);
            if(response.STATUS == "SUCCESS"){
                sweetAlert({
                    type:"success",
                    title: "Success",
                    text: "Logged in Successfully"
                });
                   $cookieStore.put('userID',response.id);
                   $cookieStore.put('username',response.username);
                   $log.log("sadfdg");
                   window.location.href= "http://localhost:8080/productapp/buyerscreen.html";

                        }
                        else {
                            sweetAlert({
                                type: "error",
                                title: "Oops..!",
                                text: "Something went wrong."
                            });
                        }
       }).error();
    }
});

productapp.controller('sellerloginController', function (apiFactory, $log, $scope,$cookieStore,$location){
    $scope.sellerlogin = function () {
        var data = {
                "username": $scope.username,
                "password":$scope.password,
        };

       apiFactory.sellerlogin(data).success(function (response){

            $log.log(response);


            if(response.STATUS == "SUCCESS"){
                            sweetAlert({
                            type:"success",
                            title: "Success",
                            text: "Logged in Successfully"
                          });

                          $cookieStore.put('userID',response.id);
                          $cookieStore.put('username',response.username);
                          $log.log($cookieStore.get('userID'));
                          window.location.href= "http://localhost:8080/productapp/sellerproducts.html";
                        }
                        else {
                            sweetAlert({
                                type: "error",
                                title: "Oops..!",
                                text: "Something went wrong."
                            });
                        }
       }).error();
    }
});

productapp.controller('buyerscreenController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.username = $cookieStore.get('username');
    $log.log($scope.username);
    $scope.logout=function(){
            $cookieStore.remove('userID');
            $cookieStore.remove('username');
            window.location.href= "http://localhost:8080/productapp/index.html";
            sweetAlert({
                        type: "success",
                        title: "OK",
                        text: "Logout Successful"
                        });
        }
    if($scope.username == undefined)
    {

        sweetAlert({
            type: "error",
            title: "Oops..!",
            text: "Please Login to continue"
            });
        window.location.href= "http://localhost:8080/productapp/index.html";
    }
});

productapp.controller('sellerproductsController', function (apiFactory, $log, $scope,$cookieStore){
    $scope.username = $cookieStore.get('username');
    $scope.logout=function(){
        $cookieStore.remove('userID');
        $cookieStore.remove('username');
        window.location.href= "http://localhost:8080/productapp/index.html";
        sweetAlert({
                    type: "success",
                    title: "OK",
                    text: "Logout Successful"
                    });
    }
    $log.log($scope.username);
    if($scope.username == undefined)
    {
        sweetAlert({
            type: "error",
            title: "Oops..!",
            text: "Please Login to continue"
            });
        window.location.href= "http://localhost:8080/productapp/index.html";
    }
});