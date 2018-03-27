	var app = angular.module("payApp", ['ngMessages']);
	app.controller('payCtrl', [ '$scope', '$http', '$rootScope','payService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		//$scope.shoppingCart=[];
		$scope.flag=0;
		$scope.tip=true;
		
		/*$scope.modifyBuyNum=function(commodityNum,symbol){
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
				};*/
				/*service.modityBuyNum(data,function(obj){
					if(obj.data.code!=1){
						window.location.href = "/login.html/"
					}else{
						service.getShoppingCart(getShoppingCartCallback);
					}
					
				});
		}*/
		
		$scope.createOrder=function(event){
			var commodity=[];
			$(".buy").each(function(index,val) {
				//console.info(val);
				var commodityNum=$(val).find(".commodityNum").val();
				var buyNum=$(val).find(".buyNum").val();
				var commodityTitle=$(val).find(".commodityTitle").text();
				var buyPrice=$(val).find(".price-now").text();
				var commodityId=$(val).find(".commodityId").text();
				if(buyNum=='0'){
					alert("购买的商品数量不能小于1哦~");
					$scope.tip=false;
					return false;
				}
				var commodityMsg = {
						'commodityNum':commodityNum,
						'buyNum':buyNum,
						'commodityTitle':commodityTitle,
						'buyPrice':buyPrice,
						'commodityId':commodityId
					};
				commodity.push(commodityMsg);
			})
			if(!$scope.tip){
				//$("#J_Go").removeClass("theme-login");
				//event.preventDefault();
				return;
			}
			var logisticsMode=$('.op_express_delivery_hot').children(".selected").text();
			var paymentMode=$('.pay-list').children(".selected").text();
			if(!logisticsMode || !paymentMode){
				alert("请选择支付方式和物流方式~");
				return;
			}
			var data={
					'paymentAmount':$scope.paymentAmount,
					'totalAmount':$scope.totalAmount,
					'receivingId':$scope.flag,
					'logisticsMode':logisticsMode,
					'paymentMode':paymentMode,
					'remark':$('#remark').val(),
					'commodity':commodity,
			}
			service.createOrder(data,function(response){
				if(response.data.code!=1){
					window.location.href = "/login.html/"
				}else{
					$scope.orderNum=response.data.orderNum;
					$("#inputPassword").click();
				}
			});
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
			$scope.totalAmount=commodityPrice+expressagePrice;
			$scope.paymentAmount=commodityPrice+expressagePrice;
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
		var createOrder=function(data,callbackFun){
			$http.post('createOrder',angular.toJson(data)).then(
	    			function (response) {
						callbackFun(response);
				});
		}
		return {
			getAllReceiving:getAllReceiving,
			createOrder:createOrder,
		/*	getReceivingById:getReceivingById,*/
			
		}
	}]);