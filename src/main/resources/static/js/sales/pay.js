	var app = angular.module("payApp", ['ngMessages']);
	app.controller('payCtrl', [ '$scope', '$http', '$rootScope','payService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		//$scope.shoppingCart=[];
		$scope.flag=0;
		
		$scope.modifyBuyNum=function(commodityNum,symbol){
			/*var buyNum=$("#buyNum").val();
			var num=symbol+1;
			buyNum=parseInt(buyNum)+parseInt(num);
			if(buyNum==0){
				alert("该商品数量不能再减少了~");
				return;
			}
			var data={
					'commodityNum':commodityNum,
					'buyNum':parseInt(num)
				};*/
				/*service.modityBuyNum(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}else{
						service.getShoppingCart(getShoppingCartCallback);
					}
					
				});*/
		}
		
		//选择用户地址
		$scope.selectAdd=function(id){
			console.info(id);
			$scope.flag=id;
			console.info($scope.flag);
		}
		
		
		//查看用户所有地址信息
		var getAllReceivingCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.receivingInfo=data.receivingList;
			}
		}
		service.getAllReceiving(getAllReceivingCallback);
		
		//总价计算
		var getPrice=function(){
			var commodityPrice = 0;
			$(".promotionPrice").each(function() {
				commodityPrice += parseFloat($(this).text());
			});
			var expressagePrice=0
			$(".expressage").each(function() {
				expressagePrice += parseFloat($(this).text());
			});
			$scope.price=commodityPrice+expressagePrice;
		}
		getPrice();
		
	
		
	}]);


	app.factory('payService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getAllReceiving=function(callbackFun){
			$http.get('findUserReceiving').then(
	    			function (response) {
						callbackFun(response.data);
			});
		};
	/*	var getReceivingById=function(id,callbackFun){
			$http.get('getReceiving?id='+id).then(
	    			function (response) {
						callbackFun(response.data);
			});
		};*/
		/*var getShoppingCart = function(callbackFun) {
			var url='shoppingCart';
			$http.get(url).then(
    			function (response) {
					callbackFun(response.data);
			});
		};
		var modityBuyNum = function(data, callbackFun) {
			var url='addToShoppingCart?commodityNum='+data.commodityNum+'&buyNum='+data.buyNum;
			$http.get(url).then(
    			function (response) {
					callbackFun(response);
			});
		};
		var delShopping=function(data,callbackFun){
			var url='delShoppingCart?commodityNums='+data.commodityNums;
			$http.delete(url).then(
	    			function (response) {
						callbackFun(response);
				});
		}*/
		return {
			getAllReceiving:getAllReceiving,
		/*	getReceivingById:getReceivingById,*/
			
		}
	}]);