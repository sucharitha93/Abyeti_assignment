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


productapp.controller('orderController',function(apiFactory,$log,$scope){
    $scope.addorder=function(){
    var data={
        "pid":$scope.pid,
        "cid":$scope.cid,
    };
    $log.log(data);
        apiFactory.addorder(data).success(function (response){
            $log.log(response);
            }).error();
    }
});



productapp.controller('vieworderController', function (apiFactory, $log, $scope){
    $scope.vieworder = function () {
        var product = {
                "oid": $scope.id,
                "pname": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "sid":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.vieworder(product).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.vieworder();
});

productapp.controller('sellersoldController', function (apiFactory, $log, $scope){
    $scope.sellersold = function () {
        var product = {
                "pid": $scope.id,
                "pname": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "id":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.sellersold(product).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.sellersold();
});

productapp.controller('sellerunsoldController', function (apiFactory, $log, $scope){
    $scope.sellerunsold = function () {
        var product = {
                "id": $scope.id,
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "sellerid":$scope.sellerid,
                "price":$scope.price
        };
       apiFactory.sellerunsold(product).success(function (response){
            $log.log(response);
            $scope.products = response;
       }).error();
    }

    $scope.sellerunsold();
});

productapp.controller('addproductController', function (apiFactory, $log, $scope){
    $scope.addproduct = function () {
        var product = {
                "name": $scope.name,
                "category":$scope.category,
                "description":$scope.description,
                "price":$scope.price
        };

       apiFactory.addproduct(product).success(function (response){

            $log.log(response);
            $scope.products = response;

            if(response.STATUS == "SUCCEEDED"){
                            sweetAlert({
                                type:"success",
                                title: "Added Sucessfully!",
                                text: "Product Added Sucessfully"
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

productapp.controller('loginController',function(apiFactory,$log,$scope){
    $scope.login=function(){
    var data={
        "username":$scope.username,
        "password":$scope.password,
        "users":$scope.users,
    };
    $log.log(data);
        apiFactory.login(data).success(function (response){
            $log.log(response);
            if(response.STATUS == "SUCCEEDED"){
                sweetAlert({
                type:"success",
                title: "Added Sucessfully!",
                text: "Product Added Sucessfully"
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
