	var app = angular.module("payApp", ['ngMessages']);
	app.controller('payCtrl', [ '$scope', '$http', '$rootScope','payService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		//$scope.shoppingCart=[];
		$scope.flag=0;
		$scope.tip=true;
		
		$scope.buyNumChange=function($event,symbol){
			var buyNum=0;
			if(symbol=='-'){
				//console.info($event);
				 buyNum=parseInt($($event.target).next().val())-1;
				// $($event.target).next().val(parseInt(buyNum)+1);
			}else{
				 buyNum=parseInt($($event.target).prev().val())+1;
				// $($event.target).prev().val(parseInt(buyNum)-1)
			}
			if(buyNum>=0){
				var promotionPrice=$($event.target).closest('.buy').prev().find('.price').text();
				var sumPrice=buyNum*parseFloat(promotionPrice)
				$($event.target).closest('.buy').next().find('.promotionPrice').text(sumPrice)
				getPrice();
			}
		};
		
		$scope.createOrder=function(event){
			var commodity=[];
			$(".buy").each(function(index,val) {
				//console.info(val);
				var commodityNum=$(val).find(".commodityNum").val();
				var buyNum=$(val).find(".buyNum").val();
				//var buyNumT=$(val).find(".buyNum").text();
				var commodityTitle=$(val).find(".commodityTitle").text();
				var buyPrice=$(val).find(".price-now").text();
				var commodityId=$(val).find(".commodityId").text();
				var cover=$(val).find('.cover').text();
				if(buyNum=='0'){
					alert("购买的商品数量不能小于1哦~");
					$scope.tip=false;
					return false;
				}
				if(!commodityNum){
					return;
				}
				var commodityMsg = {
						'commodityNum':commodityNum,
						'buyNum':buyNum,
						'commodityTitle':commodityTitle,
						'buyPrice':buyPrice,
						'commodityId':commodityId,
						'cover':cover,
					};
				commodity.push(commodityMsg);
			})
			if(!$scope.tip){
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
		};
		
		//选择用户地址
		$scope.selectAdd=function(id){
			console.info(id);
			$scope.flag=id;
			console.info($scope.flag);
		};
		
		
		//查看用户所有地址信息
		var getAllReceivingCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html"
			}else{
				//$scope.count=data.count;
				$scope.receivingInfo=data.receivingList;
				if($scope.receivingInfo.length<=0){
					window.location.href = "/address.html"
				}
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
			$scope.totalAmount=commodityPrice;
			$scope.paymentAmount=commodityPrice+expressagePrice;
		}
		getPrice();
		
		/*$scope.cancel=function(orderNum){
			
		}*/
		
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