
var app = angular.module("addressApp", ['ngMessages']);


	app.controller('addressCtrl', [ '$scope', '$http', '$rootScope','addressService', function($scope,  $http, $rootScope,service) {
		
		$scope.receivingInfo==[];
		
		$scope.saveReceiving=function(){
			var addressCity=$("#addressCity").val();
			var addressDistrict=$("#addressDistrict").val();
			var receiptAddress=$scope.addressProvince+' '+addressCity+' '+addressDistrict+' '+$scope.addressMsg;
			var data={
					'id':$scope.id,
				'consignee':$scope.consignee,
				'consigneePhone':$scope.consigneePhone,
				'receiptAddress':receiptAddress
			}
			service.saveReceivingInfo(data,function(obj){
				if(obj.data.code!=1){
					window.location.href = "/login.html/"
				}else{
				service.getAllReceiving(getAllReceivingCallback);
				}
			});
		}
		
		$scope.setDefaultAddress=function(id){
			service.setDefaultAddress(id,function(obj){
				if(obj.data.code!=1){
					window.location.href = "/login.html/"
				}else{
				service.getAllReceiving(getAllReceivingCallback);
				}
			});
		}
		var getReceiving=function(id){
			service.getReceiving(id,function(data){
				if(data.code!=1){
					window.location.href = "/login.html/"
				}else{
					$scope.id=data.id;
					$scope.consignee=data.consignee;
					$scope.consigneePhone=data.consigneePhone;
					var addressArr=data.receiptAddress.split(" ");
					$scope.addressProvince=addressArr[0];
					$("#addressCity").html(addressArr[1]);
					$("#addressDistrict").html(addressArr[2]);
					$scope.addressMsg=addressArr[3];
					//service.getAllReceiving(getAllReceivingCallback);
				}
			});
		}
		
		var getAllReceivingCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				//$scope.count=data.count;
				$scope.receivingInfo=data.receivingList;
			}
		}
		service.getAllReceiving(getAllReceivingCallback);
			
	}]);
		
  
	app.factory('addressService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		
		var saveReceivingInfo = function(data, callbackFun) {
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
		}
		return {
			saveReceivingInfo:saveReceivingInfo,
			getAllReceiving:getAllReceiving,
			setDefaultAddress:setDefaultAddress,
			getReceiving:getReceiving,
		}
		
	}]);


