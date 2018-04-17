//

var app = angular.module("orderinfoApp", ['ngMessages']);


	app.controller('orderinfoCtrl', [ '$scope', '$http', '$rootScope','orderinfoService', function($scope,  $http, $rootScope,service) {
		
		$scope.order=[];
		$scope.receiving=[];
		$scope.orderNum=$("#orderNum").text();
	
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				$scope.order=data.order;
				$scope.receiving=data.receiving;
			}
		}
		service.getOrder($scope.orderNum,getOrderCallback);
			
	}]);
		
  
	app.factory('orderinfoService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getOrder=function(orderNum,getOrderCallback){
			//status=;
			var url='getOrderInfo?orderNum='+orderNum
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		
		
		return {
			getOrder:getOrder,
		}
		
	}]);


