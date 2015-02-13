var productapp = angular.module('productapp', ['ngRoute', 'ngAnimate']);

productapp.factory("apiFactory",['$http', function($http,toaster){
    var factory = {};
    factory.login=function(data)
    {
        return $http({
            method:'POST',
            url:"http://localhost:8080/productapp/v1/api/login",
            headers: {"Content-Type" : "application/json"},
        });
    }

    factory.sellerunsold=function()
    {
                return $http({
                 method:'GET',
                 url:"http://localhost:8080/productapp/v1/api/sellerunsold",
                 headers: {"Content-Type" : "application/json"},
                 });
    }
    factory.sellersold=function()
    {
                return $http({
                 method:'GET',
                 url:"http://localhost:8080/productapp/v1/api/sellersold",
                 headers: {"Content-Type" : "application/json"},
                });
    }

    factory.vieworder=function()
        {
                    return $http({
                     method:'GET',
                     url:"http://localhost:8080/productapp/v1/api/vieworder",
                     headers: {"Content-Type" : "application/json"},
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
