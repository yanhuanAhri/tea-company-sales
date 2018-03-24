	var app = angular.module("shoppingcartApp", ['ngMessages']);
	app.controller('shoppingcartCtrl', [ '$scope', '$http', '$rootScope','shoppingcartService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		$scope.shoppingCart=[];
		
		$scope.modifyBuyNum=function(commodityNum,symbol){
			var buyNum=$("#buyNum").val();
			var num=symbol+1;
			buyNum=parseInt(buyNum)+parseInt(num);
			if(buyNum==0){
				alert("该商品数量不能再减少了~");
				return;
			}
			var data={
					'commodityNum':commodityNum,
					'buyNum':parseInt(num)
				};
				service.modityBuyNum(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}else{
						service.getShoppingCart(getShoppingCartCallback);
					}
					
				});
		}
		
		$scope.delshop=function(commodityNum){
			if(confirm("确认要删除该商品吗")){
				var data={
					'commodityNums':commodityNum,
				}
				service.delShopping(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}
				});
				service.getShoppingCart(getShoppingCartCallback);
			}else{
				return;
			}
		   
		}
		
		var getShoppingCartCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				$scope.count=data.count;
				$scope.shoppingCart=data.cartList;
			}
			
		}
		
		service.getShoppingCart(getShoppingCartCallback);

		/*function(obj){
			if(obj.data.code==1){
				alert(obj.data.msg);
			//	toastr["success"]("加入购物车成功");
			}else if(obj.data.code=404){
			//	toastr["warring"](" 您还没有登录该系统，请登录之后再进行该操作！！！");
				alert(obj.data.msg);
			}else{
				alert("加入购物车失败");
			}
		}*/
	}]);


	app.factory('shoppingcartService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {

		var getShoppingCart = function(callbackFun) {
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
		}
		return {
			getShoppingCart:getShoppingCart,
			modityBuyNum:modityBuyNum,
			delShopping:delShopping,
		}
	}]);