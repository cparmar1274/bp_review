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
  <h2>TOSCA Blueprint - Review</h2>
 
    <div class="form-group">
      <label for="Blueprint">Blueprint</label>
      <input type="text" class="form-control" id="email" ng-model="myData.bpname" placeholder="Enter blueprint yaml file path" name="Blueprint">
    </div>
    <button type="submit" class="btn btn-success" ng-click="myData.doClick(item, $event)">Submit</button>
 <h5> Blueprint Review Statistics </br> </h5> 
 <pre ng-bind-html="myData.validYaml"></pre>
 <pre ><div ng-repeat="data in myData.yamlData" ><div>{{data}}<div></br></pre>
 <pre ng-bind-html="myData.validJson"></pre>
 <pre ><div ng-repeat="data in myData.jsonData"><div>{{data}}</div></br></pre>
</div>


  <script>
    angular.module("myapp", [])
        .controller("MyController", function($scope, $http,$sce) {
        	$scope.myData = {bpname:'Enter blueprint folder path...', bpinput:'path location'};
        	$scope.myData.doClick = function(item, event) {
        		$scope.myData.fromServer = 'Reviewing Blueprint. Please wait...';
        		var data = $.param({
                    bp_path: $scope.myData.bpname
                });
                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                var responsePromise = $http.post("/TOSCABPReview/checkYaml",data,config);
                responsePromise.success(function(data, status, headers, config) {
                    $scope.myData.validYaml = $sce.trustAsHtml(data);
                  
                    //check inputs
                    var responseYamlInputPromise = $http.post("/TOSCABPReview/bpstatus",data,config);
                    responseYamlInputPromise.success(function(data, status, headers, config) {
                    	console.log("bpdata",data);
                        $scope.myData.yamlData = data;});
                    responseYamlInputPromise.error(function(data, status, headers, config) {
                   	 	$scope.myData.yamlData = "ËRROR :"+$sce.trustAsHtml(data);
                   });
                });
                responsePromise.error(function(data, status, headers, config) {
                	 $scope.myData.validYaml = "ËRROR :"+$sce.trustAsHtml(data);
                });
                
                var responseJsonPromise = $http.post("/TOSCABPReview/checkJSON",data,config);
                responseJsonPromise.success(function(data, status, headers, config) {
                    $scope.myData.validJson = $sce.trustAsHtml(data);
                    
                    //check inputs
                    var responseJsonInputPromise = $http.post("/TOSCABPReview/bpinputstatus",data,config);
                    responseJsonInputPromise.success(function(data, status, headers, config) {
                    	console.log("bp input data",data);
                        $scope.myData.jsonData = data;});
                    responseJsonInputPromise.error(function(data, status, headers, config) {
                   	 	$scope.myData.jsonData = "ËRROR :"+$sce.trustAsHtml(data);
                   });
                    
                });
                responseJsonPromise.error(function(data, status, headers, config) {
                	 $scope.myData.validJson = "ËRROR :"+$sce.trustAsHtml(data);
                });
            }


        } );
  </script>

</body>
</html>

