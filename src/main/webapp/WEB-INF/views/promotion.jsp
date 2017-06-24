<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>TOSCA Blueprint - DevOps Pipeline Helper</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body ng-app="myapp">
   <div class="container" ng-controller="MyController">
  <h2>TOSCA Blueprint - Promotion</h2>
 
    <div class="form-group">
      <label for="Blueprint">BluePrint Name</label>
      <input type="text" class="form-control" id="email" ng-model="myData.blueprintName" placeholder="Enter blueprint name" name="Blueprint Name">
      <label for="Token">Token</label>
      <input type="text" class="form-control" id="email" ng-model="myData.token" placeholder="Enter git lab token" name="Token">
    </div>
    <button type="submit" class="btn btn-success" ng-click="myData.doClick(item, $event)">Submit</button>
 <h5> BluePrint</h5> 
 <textarea ng-model="myData.blueprintFile" rows="20" cols="160" ></textarea>
 <button type="Update" class="btn btn-success" ng-click="myData.doUpdate(item, $event)">Submit</button>
 </div>


  <script>
    angular.module("myapp", [])
        .controller("MyController", function($scope, $http,$sce) {
        	$scope.myData = {};
        	$scope.myData.doClick = function(item, event) {
        		$scope.myData.fromServer = 'Uploading blueprint. Please wait...';
        		var data = $.param({
                    blueprint_name: $scope.myData.blueprintName,
                    token_name: $scope.myData.token
                });
                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                var responsePromise = $http.post("/TOSCABPReview/promotion/getBlueprintContent",data,config);
                responsePromise.success(function(data, status, headers, config) {
                    console.log(data,data.content);
                    $scope.myData.blueprintFile = angular.toJson(data, true);
                });
                responsePromise.error(function(data, status, headers, config) {
                	 $scope.myData.blueprintFile = "ËRROR :"+$sce.trustAsHtml(data);
                });
            }

        	$scope.myData.doUpdate = function(item, event) {
        		$scope.myData.fromServer = 'Uploading blueprint. Please wait...';
        		var data = $.param({
                    blueprint_name: $scope.myData.blueprintName,
                    token_name: $scope.myData.token,
                    content:$scope.myData.blueprintFile
                });
                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                var responsePromise = $http.post("/TOSCABPReview/promotion/putBlueprintContent",data,config);
                responsePromise.success(function(data, status, headers, config) {
                    console.log(data,data.content);
                    $scope.myData.blueprintFile = angular.toJson(data, true);
                });
                responsePromise.error(function(data, status, headers, config) {
                	 $scope.myData.blueprintFile = "ËRROR :"+$sce.trustAsHtml(data);
                });
            }


        } );
  </script>

</body>
</html>

