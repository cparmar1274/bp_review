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
  <h2>TOSCA Blueprint - Catalog creation</h2>
 
    <div class="form-group row">
      <label for="Catalog Name" class="col-2 col-form-label">Catalog Name</label>
      <div class="col-10">
         <input type="text" class="form-control" id="myData_catalogName" ng-model="myData.catalogName" placeholder="Enter catalog name" name="Catalog Name">
      </div>
   </div>  
   
    <div class="form-group row">
      <label for="Environments" class="col-2 col-form-label">Tag Name</label>
      <div class="col-10">
      <input type="text" class="form-control" id="myData_tagName" ng-model="myData.tagName" placeholder="Enter tag name" name="Environments">
      </div>
   </div>
   
    <div class="form-group row"> 
      <label for="Blueprint" class="col-2 col-form-label">Blueprint Name</label>
      <div class="col-10">
      <input type="text" class="form-control" id="myData_blueprintName" ng-model="myData.blueprintName" placeholder="Enter blueprint name" name="Blueprint">
      </div>
    </div>
    
     <div class="form-group row"> 
    <button type="submit" class="btn btn-success" ng-click="myData.doClick(item, $event)">Submit</button>
  </div>
   <div class="form-group row"> 
  <textarea ng-model="myData.catalogFile" rows="20" cols="160"></textarea>
  </div>
 </div>

  <script>
    angular.module("myapp", [])
        .controller("MyController", function($scope, $http,$sce) {
        	$scope.myData = {};
        	$scope.myData.doClick = function(item, event) {
        		$scope.myData.fromServer = 'Creating Catalog. Please wait...';
        		var data = $.param({
                    catalog_name: $scope.myData.catalogName,
                    tag_name: $scope.myData.tagName,
                    blueprint_name: $scope.myData.blueprintName
                });
                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                var responsePromise = $http.post("/TOSCABPReview/catalog/create",data,config);
                responsePromise.success(function(data, status, headers, config) {
                    $scope.myData.catalogFile = angular.toJson(data, true);
                  
                  
                });
                responsePromise.error(function(data, status, headers, config) {
                	 $scope.myData.catalogFile = "ËRROR :"+$sce.trustAsHtml(data);
                });
                
                
            }


        } );
  </script>

</body>
</html>

