var app = angular.module("registerApp", ['ngMessages']);


	app.controller('registerCtrl', [ '$scope', '$http', '$rootScope','registerService', function($scope,  $http, $rootScope,service) {
		
	
		/*var getMyOrder=function(status){
			service.getMyOrder(parseInt(status),getOrderCallback);
		}*/
		
		
			
	}]);
		
  
	app.factory('registerService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getHomeData=function(getHomeDataCallback){
			var url='homeData';
			$http.get(url).then(
	    			function (response) {
	    				getHomeDataCallback(response.data);
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
			getHomeData:getHomeData,
		}
		
	}]);


