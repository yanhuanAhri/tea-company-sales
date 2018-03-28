
var app = angular.module("orderApp", ['ngMessages']);


	app.controller('orderCtrl', [ '$scope', '$http', '$rootScope','orderService', function($scope,  $http, $rootScope,service) {
		
		$scope.myOrder=[]
	
		var getMyOrder=function(status){
			service.getMyOrder(parseInt(status),getOrderCallback);
		}
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.myOrder=data.myOrder;
			}
		}
		service.getMyOrder(parseInt('-1'),getOrderCallback);
			
	}]);
		
  
	app.factory('orderService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getMyOrder=function(status,getOrderCallback){
			//status=;
			var url='getMyOrder?status='+status
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		
		/*var saveReceivingInfo = function(data, callbackFun) {
			$http.post('saveReceivingInfo',angular.toJson(data)).then(
    			function (response) {
					callbackFun(response);
			});
		};
		var getAllReceiving=function(callbackFun){
			$http.get('findUserReceiving').then(
	    			function (response) {
						callbackFun(response.data);
			});
		}
		var setDefaultAddress=function(id,callbackFun){
			$http.get('defaultReceiving?id='+id).then(
	    			function (response) {
						callbackFun(response);
			});
		}
		var getReceiving=function(id,callbackFun){
			$http.get('getReceiving?id='+id).then(
	    			function (response) {
						callbackFun(response.data);
			});
		}*/
		return {
			getMyOrder:getMyOrder,
		}
		
	}]);


