
var app = angular.module("commentlistApp", ['ngMessages']);


	app.controller('commentlistCtrl', [ '$scope', '$http', '$rootScope','commentlistService', function($scope,  $http, $rootScope,service) {
		
		$scope.order=[];
		$scope.type=[];
		$scope.receiving=[];
		$scope.orderNum=$("#orderNum").text();
	
		$scope.rementType=function(commodityNum,type){//3,2,1
			$scope.type.push({'commodityNum':commodityNum,'type':type});
		}
		
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
		
  
	app.factory('commentlistService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
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
