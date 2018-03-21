
var app = angular.module("introdutionApp", []);


	app.controller('introductionCtrl', [ '$scope', '$http', '$rootScope','introductionService', function($scope,  $http, $rootScope,service) {
		
		$scope.addToShoppingCart=function(){
			var commodityNum=$("#commodityNum").val();
			var buyNum= $("input[name=buyNum]").val();
			 var data={
				'commodityNum':commodityNum,
				'buyNum':buyNum
			};
			service.addToShopingCart(data,function(obj){
			
				if(obj.data.code==1){
					alert(obj.data.msg);
				//	toastr["success"]("加入购物车成功");
				}else if(obj.data.code=404){
				//	toastr["warring"](" 您还没有登录该系统，请登录之后再进行该操作！！！");
					alert(obj.data.msg);
				}else{
					alert("加入购物车失败");
				}
			});
		}
		
		
	}]);
		
	//}
  
	app.factory('introductionService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		
		var addToShopingCart = function(data, callbackFun) {
		
			var url='addToShoppingCart?commodityNum='+data.commodityNum+'&buyNum='+data.buyNum;
			$http.get(url).then(
    			function (response) {
					callbackFun(response);
			});

		};
			return {
				addToShopingCart:addToShopingCart,
			}
	}]);


