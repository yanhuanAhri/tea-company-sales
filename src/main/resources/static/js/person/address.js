
var app = angular.module("addressApp", ['ngMessages']);


	app.controller('addressCtrl', [ '$scope', '$http', '$rootScope','addressService', function($scope,  $http, $rootScope,service) {
		
		$scope.receivingInfo==[];
		$scope.addressProvinceArr=[];
		$scope.addressCityArr=[];
		$scope.addressDistrictArr=[];
		
		
		$scope.onChange=function(name,level){
			selectionChange(name,level);
		}
		var selectionChange=function(name,level){
			console.info(name,level);
			/*console.info("$scope.addressProvinceArr:"+$scope.addressProvinceArr);
			console.info("$scope.addressCityArr"+$scope.addressCityArr);
			console.info("$scope.addressDistrictArr"+$scope.addressDistrictArr);*/
			
			var arr=[]
			if(level==1){
				arr=$scope.addressProvinceArr;
			}else if(level==2){
				arr=$scope.addressCityArr;
			}else if(level==3){
				arr=$scope.addressDistrictArr;
			}
			/*console.info("arr:"+arr)*/
			var id=0;
			for(var i=0;i<arr.length;i++){
				if(name==arr[i].name){
					id=arr[i].id;
					break;
				}
			}
			service.getArea(id,function(data){
				if(data[0].level==2){
					$scope.addressCityArr=data;
					$scope.addressDistrictArr=[];
				}else if(data[0].level==3){
					$scope.addressDistrictArr=data;
				}
			})
		}
		
		$scope.saveReceiving=function(){
			if(!$scope.addressProvince || !$scope.addressCity || !$scope.addressDistrict){
				alert("请选择所在地！！！")
				return;
			}
			var receiptAddress=$scope.addressProvince+' '+$scope.addressCity+' '+$scope.addressDistrict+' '+$scope.addressMsg;
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
					
					$scope.addressProvince= addressArr[0] ;
					/*service.addressProvince(function(data){
						$scope.addressProvinceArr=data;
					})*/
					selectionChange(addressArr[0],1);
					$scope.addressCity=addressArr[1];
					selectionChange(addressArr[1],2);
					
					$scope.addressDistrict=addressArr[2];
					
					
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
		
		service.addressProvince(function(data){
			$scope.addressProvinceArr=data;
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
		var addressProvince=function(callbackFun){
			$http.get('getArea').then(
	    			function (response) {
						callbackFun(response.data);
			});
		}
		var getArea=function(id,callbackFun){
			$http.get('getArea?parentId='+id).then(
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
			addressProvince:addressProvince,
			getArea:getArea
		}
		
	}]);


