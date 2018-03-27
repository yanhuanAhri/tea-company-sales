	var app = angular.module("shoppingcartApp", ['ngMessages']);
	app.controller('shoppingcartCtrl', [ '$scope', '$http', '$rootScope','shoppingcartService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		$scope.shoppingCart=[];
		$scope.commodityNums=[];
		$scope.selectAll=false;
		$scope.totalAmount=0;
		
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
		//单个删除
		$scope.delshop=function(commodityNum){
			if(confirm("确认要删除该商品吗")){
				var data={
					'commodityNums':commodityNum,
				}
				service.delShopping(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}else{
						service.getShoppingCart(getShoppingCartCallback);
					}
				});
			}
		}
		//删除所有
		$scope.delAll=function(){
			if(confirm("确认要删除这些商品吗")){
				var data={
						'commodityNums':$scope.commodityNums,
					}
				service.delShopping(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}else{
						service.getShoppingCart(getShoppingCartCallback);
					}
				});
			}
		}
		//下单
		$scope.settleAccounts=function(){
			if($scope.commodityNums.length!=0){
				$("#buyForm").attr('action',"/buyCommodity?shoppingCart=''");//?commodityNum="+commodityNum
				 $("#buyForm").submit();
			}else{
				alert("请先选择一个商品~");
			}
		}
		//全选
		$scope.allSelect=function(){
			$scope.commodityNums=[];
			$scope.totalAmount=0;
			if($scope.selectAll){
				$scope.selectAll=false;
			}else{
				for(var i=0;i<$scope.shoppingCart.length;i++){
					$scope.commodityNums.push($scope.shoppingCart[i].commodityNum);
					$scope.totalAmount=parseFloat($scope.totalAmount)+parseFloat($scope.shoppingCart[i].promotionPrice)*parseInt($scope.shoppingCart[i].buyNum);
				}
				$scope.selectAll=true;
			}
		}
		//单选
		$scope.select=function(commodityNum,price){
			var index=$.inArray(commodityNum, $scope.commodityNums);//不包含在数组中,则返回 -1
			if(index!='-1'){
				$scope.commodityNums.splice(index,1);
				$scope.totalAmount=parseFloat($scope.totalAmount)-parseFloat(price);
			}else{
				$scope.commodityNums.push(commodityNum)
				$scope.totalAmount=parseFloat($scope.totalAmount)+parseFloat(price);
			}
			if($scope.commodityNums.length==$scope.count){
				$scope.selectAll=true;
			}else{
				$scope.selectAll=false;
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