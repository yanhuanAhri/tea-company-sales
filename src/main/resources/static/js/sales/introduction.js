
var app = angular.module("introdutionApp", []);


	app.controller('introductionCtrl', [ '$scope', '$http', '$rootScope','introductionService', function($scope,  $http, $rootScope,service) {
		$scope.buyNum=1;
		
		$scope.addToShoppingCart=function(){
			var commodityNum=$("#commodityNum").val();
			//var buyNum= $("input[name=buyNum]").val();
			if(buyNum<2){
				buyNum=1;
			}
			 var data={
				'commodityNum':commodityNum,
				'buyNum':$scope.buyNum
			};
			service.addToShopingCart(data,function(obj){
				if(obj.data.code!=1){
					window.location.href = "/login.html/"
				}else{
					alert(obj.data.msg);
				//	toastr["success"]("加入购物车成功");
				}
			});
		}

		$scope.buyNumChange=function($event,symbol){
			
			if(symbol=='-'){
				//console.info($event);
				$scope.buyNum=parseInt($($event.target).next().val())-1;
				// $($event.target).next().val(parseInt(buyNum)+1);
			}else{
				var productNum=$("#productNum").text();
				if(parseInt($scope.buyNum)<parseInt(productNum)){
					$scope.buyNum=parseInt($($event.target).prev().val())+1;
				}else{
					alert("购买数量不能大于库存量！！！");
				}
				
				// $($event.target).prev().val(parseInt(buyNum)-1)
			}
		};
		$scope.buyNow=function(){
			var commodityNum=$("#commodityNum").val();
			$("#buyNum").val($scope.buyNum);
			 var data={
				'commodityNum':commodityNum,
				'buyNum':$scope.buyNum,
				'type':'C'
			};
			 $("#buyForm").attr('action',"/buyCommodity?commodityNum="+commodityNum);
			 $("#buyForm").submit();
		}
		service.getTeaSet(function(data){
			$scope.teaSet=data.teaSet;
		});
		var getProductType=function(){
			$scope.productType=$("#productType").text();
			/*var count=$("#shopCartCount").text();
			console.info($("#shopCartCount"));*/
		}
		getProductType();
	}]);
		
	//}
  
	app.factory('introductionService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getTeaSet=function(getTeaSetCallbackFun){
			$http.get('teaSet').then(
	    			function (response) {
	    				getTeaSetCallbackFun(response.data);
				});
		}
		
		var addToShopingCart = function(data, callbackFun) {
			var url='addCommodityToShopCart?commodityNum='+data.commodityNum+'&buyNum='+data.buyNum;
			$http.get(url).then(
    			function (response) {
					callbackFun(response);
			});
		};
		
		var buyNow=function(data){
			$http.post("buyCommodity",angular.toJson(data)).then(function (response) {});
		}
		return {
			getTeaSet:getTeaSet,
			addToShopingCart:addToShopingCart,
			buyNow:buyNow,
		}
		
	}]);


