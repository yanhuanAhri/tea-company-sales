
var app = angular.module("orderApp", ['ngMessages']);


	app.controller('orderCtrl', [ '$scope', '$http', '$rootScope','orderService', function($scope,  $http, $rootScope,service) {
		
		$scope.myOrder=[]
	
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.myOrder=data.myOrder;
			}
		}
		service.getMyOrder(null,getOrderCallback);
			
	}]);
		
  
	app.factory('orderService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getMyOrder=function(status,getOrderCallback){
			//status=;
			var url;
			if(status==null){
				url='getMyOrder'
			}else{
				 url='getMyOrder?status='+status
			}
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		
		
		return {
			getMyOrder:getMyOrder,
		}
		
	}]);


