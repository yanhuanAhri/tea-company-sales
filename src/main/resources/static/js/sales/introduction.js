
var app = angular.module("introdutionApp", []);


	app.controller('introductionCtrl', [ '$scope', '$http', '$rootScope','introductionService', function($scope,  $http, $rootScope,service) {
		
		$scope.addToShoppingCart=function(){
			var commodityNum=$("#commodityNum").val();
			var buyNum= $("input[name=buyNum]").val();
			if(buyNum<2){
				buyNum=1;
			}
			 var data={
				'commodityNum':commodityNum,
				'buyNum':buyNum
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
		
		$scope.buyNow=function(){
			var commodityNum=$("#commodityNum").val();
			var buyNum= $("input[name=buyNum]").val();
			if(buyNum<2){
				buyNum=1;
			}
			$("#buyNum").val(buyNum);
			 var data={
				'commodityNum':commodityNum,
				'buyNum':buyNum,
				'type':'C'
			};
			 $("#buyForm").attr('action',"/buyCommodity?commodityNum="+commodityNum);
			 $("#buyForm").submit();
			// $('#buyForm').attr('action':'buyCommodity');
			// $('#buyForm').submit('msg':data);
			 //('/buyCommodity', { 'msg': data},'post')
			// service.buyNow(data);
		}
		
	}]);
		
	//}
  
	app.factory('introductionService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		
		var addToShopingCart = function(data, callbackFun) {
			var url='addCommodityToShopCart?commodityNum='+data.commodityNum+'&buyNum='+data.buyNum;
			$http.get(url).then(
    			function (response) {
					callbackFun(response);
			});
		};
		
		var buyNow=function(data){
			/*var fileParams = new FormData();
            fileParams.append('msg', angular.toJson(data));
            $http({
                method:'POST',
                url: 'buyCommodity',
                data: fileParams,
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
               }).then(function (response) {
                });*/
            
			//不能用ajax提交，待续……
			$http.post("buyCommodity",angular.toJson(data)).then(function (response) {});
		}
		return {
			addToShopingCart:addToShopingCart,
			buyNow:buyNow,
		}
		
	}]);


