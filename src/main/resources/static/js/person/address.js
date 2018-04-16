
var app = angular.module("addressApp", ['ngMessages']);


	app.controller('addressCtrl', [ '$scope', '$http', '$rootScope','addressService', function($scope,  $http, $rootScope,service) {
		
		$scope.receivingInfo==[];
		
		$scope.saveReceiving=function(){
			var addressCity=$("#addressCity").val();
			var addressDistrict=$("#addressDistrict").val();
			$(".addressCity").find("option:selected").text();
			if(!$scope.addressProvince || !addressCity || addressDistrict){
				alert("请选择所在地！！！")
				return;
			}
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
		$scope.delClick=function(id){
			service.delReceiving(id,function(data){
				if(data.code!=1){
					window.location.href = "/login.html/"
				}else{
					service.getAllReceiving(getAllReceivingCallback);
				}
			})
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
					
					$("#addressProvince").html('<option selected>' + addressArr[0] + "</option>");
					$("#addressCity").html('<option selected>' + addressArr[1] + "</option>");
					$("#addressDistrict").html('<option selected>' + addressArr[2] + "</option>");
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
		$scope.area=[];
		service.getArea(function(data){
			$scope.area=data;
		})
		//getArea
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
		var delReceiving=function(id,callbackFun){
			$http.delete('delReceiving?id='+id).then(
	    			function (response) {
						callbackFun(response.data);
			});
		}
		var getArea=function(callbackFun){
			$http.get('getArea').then(
	    			function (response) {
						callbackFun(response.data);
			});
		}
		return {
			saveReceivingInfo:saveReceivingInfo,
			getAllReceiving:getAllReceiving,
			setDefaultAddress:setDefaultAddress,
			getReceiving:getReceiving,
			delReceiving:delReceiving,
			getArea:getArea
		}
		
	}]);


