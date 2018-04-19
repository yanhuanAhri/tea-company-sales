
var app = angular.module("orderApp", ['ngMessages']);


	app.controller('orderCtrl', [ '$scope', '$http', '$rootScope','orderService', function($scope,  $http, $rootScope,service) {
		
		$scope.myOrder=[]

		$scope.payClick=function(orderNum,paymentAmount){
			$scope.payOrderNum=orderNum;
			$scope.paymentAmount=paymentAmount
		}
		$scope.cancelClick=function(orderNum){
			$scope.cancelOrderNum=orderNum;
		}
		$scope.remind=function(){
			alert("已提醒卖家发货");
		}
		
		$scope.delOrder=function(orderNum){
			if(confirm("确认要删除该订单吗")){
				var data={
						orderNum:orderNum
				}
				service.delOrder(data,function(data){
					if(data.code==0){
						window.location.href = "/home.html";
					}else if(data.code==1){
						service.getMyOrder(null,getOrderCallback);
						alert("删除成功");
					}else{
						window.location.href = "/login.html";
					}
				});
			}
			
		}
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.myOrder=data.myOrder;
			}
		}
		service.getMyOrder(null,getOrderCallback);
			
	}]);
		
  
	app.factory('orderService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getMyOrder=function(status,getOrderCallback){
			//status=;
			var url;
			if(status==null){
				url='getMyOrder'
			}else{
				 url='getMyOrder?status='+status
			}
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		//delOrder
		var delOrder = function(data, callbackFun) {
			$http.post('delOrder',angular.toJson(data)).then(
    			function (response) {
					callbackFun(response.data);
			});
		};
		
		return {
			getMyOrder:getMyOrder,
			delOrder:delOrder,
		}
		
	}]);

	$(document).on('click', '.theme-login', function() {
		$('.dlg-bg,.dlg1').show()
	})
	$(document).on('click', '.orderCancel', function() {
		$('.dlg-bg,.dlg2').show()
	})
	
	$(document).on('click', '.theme-close', function() {
		$('.dlg-bg,.dlg1,.dlg2').hide()
	})

