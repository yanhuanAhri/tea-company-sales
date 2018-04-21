
var app = angular.module("commentlistApp", ['ngMessages']);


	app.controller('commentlistCtrl', [ '$scope', '$http', '$rootScope','commentlistService', function($scope,  $http, $rootScope,service) {
		
		$scope.order=[];
		$scope.type=[];
		$scope.receiving=[];
		$scope.orderNum=$("#orderNum").text();
		var defaultPicUrl = 'images/picadd.jpg';
	
		$scope.rementType=function(commodityNum,type){//3,2,1
			$scope.type.push({'commodityNum':commodityNum,'type':type});
		}
		
	       setTimeout(function() {
	        	$('.img-del').hide();
			            // 点击上传图片事件
			            $("img").unbind().click(function() {
			                $(this).prev().click();
			            });
			            // 点击图片取消事件
			            $('.img-del').unbind().click(function() {
			                if ($(this).prev().attr('src') != defaultPicUrl) {
			                	//将图片路径重置为默认图片
			                    $(this).prev().attr('src', defaultPicUrl);
			                    //将input重置为空 
			                    $(this).prev().prev().val('');
			                    $(this).attr('src', "");
			                    //图片删除标记
			                }
			            });
			            // 移出、移入图片事件
			            $('.file').unbind().mouseover(function () {
			                if ($(this).find('img').attr('src') != defaultPicUrl) {
			                    $(this).find('.img-del').show();
			                }
			            }).mouseout(function () {
			                $(this).find('.img-del').hide();
			            });
			        }, 100);
	        
			
	           //提交上传事件
		       $scope.uploadFile = function(inputDomId) {
					 //  var file = document.querySelector('#' + domId).files[0];
		           utils.getImgBase64(inputDomId, function(base64) {
		               if (base64 == 'error') {
		                   toastr["warning"]("文件格式错误，只支持jpg、png、gif、jpeg");
		                   $('#'+inputDomId).val("");
		               } else {
		                   if (utils.getFileSize(base64, 'file')) {
		                       toastr["warning"]("文件格式不能大于5MB");
		                       $('#'+inputDomId).val("");
		                   }else{
		                   	//$('#'+inputDomId).next().show();
		                   	$('#'+inputDomId).next().attr('src', base64);
		                   }
		               }
		           });
		      	 
		       }
		
		var getOrderCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				$scope.order=data.order;
				$scope.receiving=data.receiving;
			}
		}
		service.getOrder($scope.orderNum,getOrderCallback);
			
	}]);
		
  
	app.factory('commentlistService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		var getOrder=function(orderNum,getOrderCallback){
			//status=;
			var url='getOrderInfo?orderNum='+orderNum
			$http.get(url).then(
	    			function (response) {
	    				getOrderCallback(response.data);
			});
		}
		
		
		return {
			getOrder:getOrder,
		}
		
	}]);
