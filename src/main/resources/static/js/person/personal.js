
var app = angular.module("personalApp", ['ngMessages']);


	app.controller('personalCtrl', [ '$scope', '$http', '$rootScope','personalService', function($scope,  $http, $rootScope,service) {
		
		$scope.myOrder=[]
	
		/*var getMyOrder=function(status){
			service.getMyOrder(parseInt(status),getOrderCallback);
		}*/
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.myOrder=data.myOrder;
			}
		}
		service.getMyOrder(getOrderCallback);
			
	}]);
		
  
	app.factory('personalService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getMyOrder=function(getOrderCallback){
			var status=[0,2,3,4,10];
			var url='getMyOrder?status='+status
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		
		
		return {
			getMyOrder:getMyOrder,
		}
		
	}]);


