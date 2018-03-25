
var app = angular.module("addressApp", ['ngMessages']);


	app.controller('addressCtrl', [ '$scope', '$http', '$rootScope','addressService', function($scope,  $http, $rootScope,service) {
		
		$scope.receivingInfo==[];
		
		$scope.saveReceiving=function(){
			var addressCity=$("#addressCity").val();
			var addressDistrict=$("#addressDistrict").val();
			$(".addressCity").find("option:selected").text();
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
				$("input").val('');
				$("textarea").val('');
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
		$scope.getReceiving=function(id){
			service.getReceiving(id,function(data){
				if(data.code!=1){
					window.location.href = "/login.html/"
				}else{
					$scope.id=data.id;
					$scope.consignee=data.receiving.consignee;
					$scope.consigneePhone=data.receiving.consigneePhone;
					var addressArr=data.receiving.receiptAddress.split(" ");
					//$scope.addressProvince=addressArr[0];
					//$scope.addressCity=addressCity[1];
					//data-code=42100
					//$("#addressCity").attr('data-code','420100');//val(addressArr[1]);
					/*(if($("#addressCity").child.val()==addressCity[1]){
						($("#addressCity").child.attr("selected", "selected")
					});*/
					//待修改
					selection('addressProvince',addressArr[0]);
					selection('addressCity',addressArr[1]);
					//text(addressCity[1]);
					$("#addressDistrict").text(addressArr[2]);
					$scope.addressMsg=addressArr[3];
					//service.getAllReceiving(getAllReceivingCallback);
				}
			});
		}
		
		var selection=function(id,value){
			var lis=$('#'+id).find('option');
			//$('#'+parentId).find('option').val=parentValue;
			$.each(lis,function(index,val){
				//var flg=$(val).find("input[type='checkbox']").is(':checked')
				console.info(val);
				console.info(value);
				if($(val).val()==value){
					$(val).attr("selected", "selected");
				};
			})
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


