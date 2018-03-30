
var app = angular.module("mainApp", ['ngMessages']);


	app.controller('mainCtrl', [ '$scope', '$http', '$rootScope','mainService', function($scope,  $http, $rootScope,service) {
		
		//全局变量
		$scope. productType=[
			{
				'productType':'乌龙茶',
				'teaName':[{'value':'安溪铁观音'},{'value':'武夷岩茶'},{'value':'台湾高山茶'},{'value':'广东单枞'},
						{'value':'漳平水仙'},{'value':'其他乌龙茶'}]
			},
			{
				'productType':'红茶',
				'teaName':[{'value':'金骏眉'},{'value':'正山小种'},{'value':'祁门红茶'},{'value':'云南滇红'},
						{'value':'凌云白毫红茶'},{'value':'英德红茶'},{'value':'锡兰红茶'},{'value':'其他红茶'}]
			},
			{
				'productType':'绿茶',
				'teaName':[{'value':'龙井'},{'value':'碧螺春'},{'value':'黄山毛峰'},{'value':'六安瓜片'},{'value':'太平猴魁'},
						{'value':'安吉白茶'},{'value':'信阳毛尖'},{'value':'云南滇绿'},{'value':'凌云白毫绿茶'},{'value':'四川绿茶'}]
			},
			{
				'productType':'黑茶',
				'teaName':[{'value':'普洱茶'},{'value':'安化黑茶'},{'value':'柑普茶'}]
			},
			{
				'productType':'白茶',
				'teaName':[{'value':'福鼎白茶'},{'value':'其他白茶'}]
			},
			{
				'productType':'花茶',
				'teaName':[{'value':'茉莉花茶'},{'value':'玫瑰花茶'},{'value':'菊花茶'},{'value':'其他花茶'}]
			},
			{
				'productType':'茶器',
				'teaName':[{'value':'陶瓷茶具'},{'value':'紫砂茶具'},{'value':'玻璃茶具'},{'value':'茶盘'},
						{'value':'建盏'},{'value':'茶道配件'},{'value':'铁艺'}]
			}
		];	
		/*var getMyOrder=function(status){
			service.getMyOrder(parseInt(status),getOrderCallback);
		}*/
		
		var getHomeDataCallback= function(data) {
			//$scope.myOrder=data.myOrder;
			$scope.data=data;
			/*$scope.newCommodity=data.newCommodity;
			$scope.hotCommodity=data.hotCommodity;
			$scope.oolong=data.oolong;
			$scope.blackTea=data.blackTea;
			$scope.greenTea=data.greenTea;
			$scope.darkGreenTea=data.darkGreenTea;
			$scope.whiteTea=data.whiteTea;
			$scope.scentedTea=data.scentedTea;
			$scope.teaSet=data.teaSet;*/
		}
		service.getHomeData(getHomeDataCallback);
			
	}]);
		
  
	app.factory('mainService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
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
			getMyOrder:getMyOrder,
		}
		
	}]);


