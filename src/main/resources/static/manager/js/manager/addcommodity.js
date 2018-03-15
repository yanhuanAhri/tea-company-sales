
	var app = angular.module('addcommodityApp', ['ngTouch', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.resizeColumns', 'ui.grid.pagination','toggle-switch']);

	
	
	
	app.controller('addcommodityController', [ '$scope', 'i18nService', '$http', 'uiGridConstants', '$uibModal', 'addcommodityService','$interval', function($scope, i18nService, $http, uiGridConstants, $modal, service,$interval) {
        /*--------------------------------------------------------------
         | 全局变量
         |--------------------------------------------------------------
        */
        var rowSelectionIdArr  = [];
        var departmentGridApi = null;
        var mechanismData = []; //表格所属机构下拉搜索数据
        var subscriptionData = []; //表格角色下拉搜索数据
		var $ctrl = this;
		var pageSize = 25;
		var sort = null;
		var filter = null;
		
		
        //表格语言中文
        i18nService.setCurrentLang("zh-cn");

		$ctrl.animationsEnabled = true;

		$scope.tableHeight = 'height: 800px';
		$scope.showOrHide = '隐藏过滤';

		$scope.switchStatus = true;
		
		
		$scope.orgData = [];
        $scope.orgId = '';
        var defaultPicUrl = 'images/picadd.jpg';
        var $ctrl = this;
		
		/*var btnCols = '<div class="line-height-30 line-center"><a href="#/addcommodity/look" ng-click="grid.appScope.openLookWin(row.entity.id)"  data-toggle="tooltip" data-placement="left" title="查看" class="btn btn-social-icon btn-xs btn-bitbucket"><i class="fa fa-fw fa-eye"></i></a>' +
		'</div>';*/
		
	/*	$scope.clearDate = function() {
			alert(123)
		}*/

		var paginationOptions = {
			useExternalSorting: true,
			pageNumber: 1,
		    pageSize: 25,
		    sort: null
		};

		
		

		var getPageCallbackFun = function(response) {
			$scope.gridOptions.totalItems = response.total;
			$scope.gridOptions.data = response.data;
			
		};

        //获取表格订阅号下拉数据
		/*service.getaddcommoditySubscriptions(officialCount);*/
		
		//formula()
		$scope.showInfo = false;
		
		$scope.formula=function(){
		     $scope.showInfo = true;  
		}
		$scope.closeInfo=function(){
		     $scope.showInfo = false;  
		}

		$scope.addCommodity = function() {
           var data = {
               'productNum': $scope.productNum,
               'tradeName':$scope.tradeName,
               'markePrice': $scope.markePrice,
               'promotionPrice':$scope.promotionPrice,
               'teaName': $scope.teaName,
               'tradeName':$scope.tradeName,
               'productType': $scope.productType,
               'pickYear':$scope.pickYear,
               'pickSeason': $scope.pickSeason,
               'goodsGrade':$scope.goodsGrade,
               'netContent': $scope.netContent,
               'purpose':$scope.purpose,
             /*  
               'markePrice': $scope.tradeName,
               'secondReceivable': secondReceivable,
           	'thirdReceivable': thirdReceivable,
               //$scope.thirdReceivable,
               'remark': $scope.remark,
               //文件路径 收款截图和发票截图
               //null ? '':document.querySelector('#mymaterial-file').files[0]
               'gathering1':document.querySelector('#gathering1').files[0],
               'gathering2':document.querySelector('#gathering2').files[0],
               'gathering3':document.querySelector('#gathering3').files[0],
               'invoice':document.querySelector('#invoice').files[0],
              */
           };
         //  console.info(items);

			service.savecommodity(data, items, function() {
			/*	$uibModalInstance.close();
				$uibModalInstance.close();*/
				//id type
				 var flag=items[0].type;
				 items[0].id=items[0].type+items[0].id;
	  			$ctrl.items = items;
	  			
			});
		}


		$scope.toggleFiltering = function(){
			$scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
			$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );

			$scope.showOrHide = $scope.gridOptions.enableFiltering ? '隐藏过滤' : '显示过滤';
			
		};
	}]);



	
	app.controller('SeeImgController', ['$scope', '$uibModalInstance', 'items', function ($scope, $uibModalInstance, items) {
        $scope.imgUrl = items[0].imgUrl;
        $scope.title = '查看';
        $scope.buttonName = '确定';
        $scope.loading = true;

        $scope.cancel = function() {
            $uibModalInstance.dismiss();
        };

        $scope.funcName = $scope.cancel;
    }]);


	app.controller('CloseAccountController', [ '$scope', '$http', 'uiGridConstants', '$uibModalInstance', 'addcommodityService', '$interval', 'items', '$filter','$uibModal', function($scope, $http, uiGridConstants, $uibModalInstance, service, $interval, items, $filter,$modal) {
        $scope.orgData = [];
        $scope.orgId = '';
        var defaultPicUrl = 'images/picadd.jpg';
        var $ctrl = this;
        
		$scope.cancel = function() {
			 $uibModalInstance.dismiss();
		};
		
		$scope.onChange = function() {
		    $scope.firstPercen= $scope.payAmount==0 ? '0.00%': ( parseFloat($('#firstReceivable').val()/ $scope.payAmount*100).toFixed(2)+'%');
		    $scope.secondPercen= $scope.payAmount==0 ? '0.00%': ( parseFloat($('#secondReceivable').val()/$scope.payAmount*100).toFixed(2)+'%');
		    $scope.thirdPercen= $scope.payAmount==0 ? '0.00%': ( parseFloat($('#thirdReceivable').val()/$scope.payAmount*100).toFixed(2)+'%');
		}
		
		  //收款详情
		  service.getOrderCollection(items,function(obj,items) {
			    $scope.payAmount=items[0].payAmount;
			    $scope.type=items[0].type
		    	$scope.id=items[0].id
			    $scope.firstReceivable= obj.firstReceivable==null? obj.firstReceivable:obj.firstReceivable.toFixed(2);
			    $scope.firstPercen= items[0].payAmount==0 ? '0.00%': (obj.firstReceivable==null ? '0.00%' : parseFloat(obj.firstReceivable/items[0].payAmount*100).toFixed(2)+'%')
       			$scope.secondReceivable= obj.secondReceivable==null? obj.secondReceivable:obj.secondReceivable.toFixed(2);
			    $scope.secondPercen= items[0].payAmount==0 ? '0.00%': (parseFloat(obj.secondReceivable/items[0].payAmount*100).toFixed(2)+'%')
       			$scope.thirdReceivable= obj.thirdReceivable==null? obj.thirdReceivable:obj.thirdReceivable.toFixed(2);
			    $scope.thirdPercen= items[0].payAmount==0 ? '0.00%': (parseFloat(obj.thirdReceivable/items[0].payAmount*100).toFixed(2)+'%')
	        })
	        
	        
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
			                   /* var inputId = $(this).prev().prev().prop("id");
			                    if(inputId == 'pic'){// 广告图
			                    	$scope.pic = '';
			                    }else if(inputId == 'J-contractPic1Input'){// 合同1
			                    	$scope.contractUrl1 = '';
			                    }else if(inputId == 'J-contractPic2Input'){// 合同2
			                    	$scope.contractUrl2 = '';
			                    }else if(inputId == 'J-contractPic3Input'){// 合同3
			                    	$scope.contractUrl3 = '';
			                    }else if(inputId == 'J-qualificationInput'){// 资质
			                    	$scope.qualificationUrl = '';
			                    }*/
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
		  
		 

		$scope.addOrder = function() {
			
			 if(document.querySelector('#invoice').files[0]==null){
				  //$scope.payAmount
				  if($('#thirdReceivable').val()!=null&&$('#thirdReceivable').val()!=''){
					  var count= parseInt($scope.firstReceivable+$scope.secondReceivable+parseInt($('#thirdReceivable').val()));
					  if(count<$scope.payAmount){
						  toastr["warning"]("三期收款总金额不能小于实付金额，三期百分比不能小于百分百！！！");
				            return;
					  }
				  }
				 
				  var thirdReceivable=''
				  if($('#thirdReceivable').val()!=null && $('#thirdReceivable').val()!=''){
	              	 thirdReceivable= parseFloat($('#thirdReceivable').val());
	              }
				  var secondReceivable=''
					  if($('#secondReceivable').val()!=null && $('#secondReceivable').val()!=''){
						  secondReceivable= parseFloat($('#secondReceivable').val());
		              }
				  var firstReceivable=''
					  if($('#firstReceivable').val()!=null && $('#firstReceivable').val()!=''){
						  firstReceivable= parseFloat($('#firstReceivable').val());
		              }
				  
				  if(firstReceivable==''||firstReceivable<=0){
					  toastr["warning"]("收款金额不能小于等于零！！！");
			            return;
				  }
				 
				  if(document.querySelector('#gathering1').files[0]==null&&document.querySelector('#gathering2').files[0]==null&&document.querySelector('#gathering3').files[0]==null)	{
					  toastr["warning"]("必须上传一张收款截图！！！");
			            return;
				  }
				  var third=parseFloat( thirdReceivable==''? 0:thirdReceivable);
				  var second=parseFloat( secondReceivable==''?0:secondReceivable);
				  var first=parseFloat( firstReceivable==''?0:firstReceivable);
				  var sum=parseFloat( third) + parseFloat( second) + parseFloat( first);
				  if(sum>$scope.payAmount){
					  toastr["warning"]("收款总金额不能大于应付金额");
			            return;
				  }
				  if($('#remark').val()!=null&&$('#remark').val()!=''){
					  if($scope.remark.indexOf("#")>=0 || $scope.remark.indexOf("'")>=0 || $scope.remark.indexOf("%")>=0 || $scope.remark.indexOf("&")>=0 || $scope.remark.indexOf("_")>=0) {
			                toastr["warning"]("备注内容不可包含“#、'、%、&、_等字符”");
			                return;
			            }
				  }
			  
			 }
			  
			 
              
            var data = {
                'id': $scope.id,
                'type':$scope.type,
                
                'firstReceivable': firstReceivable,
                'secondReceivable': secondReceivable,
            	'thirdReceivable': thirdReceivable,
                //$scope.thirdReceivable,
                'remark': $scope.remark,
                //文件路径 收款截图和发票截图
                //null ? '':document.querySelector('#mymaterial-file').files[0]
                'gathering1':document.querySelector('#gathering1').files[0],
                'gathering2':document.querySelector('#gathering2').files[0],
                'gathering3':document.querySelector('#gathering3').files[0],
                'invoice':document.querySelector('#invoice').files[0],
               
            };
          //  console.info(items);

			service.addOrder(data, items, function() {
				$uibModalInstance.close();
				$uibModalInstance.close();
				//id type
				 var flag=items[0].type;
				 items[0].id=items[0].type+items[0].id;
	  			$ctrl.items = items;
	  			
			});
		}
	}]);



    app.factory('addcommodityService', ['$q', '$filter', '$timeout', '$http', function ($q, $filter, $timeout, $http) {
    	
    	var saveCommodity = function(data, items, closeFun) {
			   var fileParams = new FormData();
	            fileParams.append('msg', angular.toJson(data));
	            fileParams.append('invoice', data.invoice);
	            fileParams.append('gathering1', data.gathering1);
	            fileParams.append('gathering2', data.gathering2);
	            fileParams.append('gathering3', data.gathering3);
	            $http({
	                method:'POST',
	                url: 'manager/saveCommodity',
	                data: fileParams,
	                headers: {'Content-Type': undefined},
	                transformRequest: angular.identity
	               }).success(function (response) {
	                    toastr["success"]('更新成功');
	                    closeFun();
	                    items[2](items,items[3]);
	                }).error(function(response, status, headers, congfig) {
	            });
		}
    	
		/*var getPage = function(page, pageSize, sort, filter, callbackFun) {
			//addcommodity/pages/{pageNumber}
			var url = '/addcommodity/pages/' + (page -1) + '?pageSize=' + pageSize;
			url = url + '&filter=' + (filter ? angular.toJson(filter) : '');
			url = url + '&sort=' +  (sort ? sort : '');

			$http.get(url)
				.success(function (response) {
					if(!callbackFun) {
						return;
					}
					callbackFun(response);
				});
			
		};
	
		
		
		var getOrderCollection = function(items,callbakcFun) {
			var id=items[0].id;
			
			if(id.length>1){
				id=id.substring(1,id.length);
			}
	    	$http.get('/order?type='+items[0].type+'&id='+id, null)
	    	.then(
	    			function successCallback(response) {
	    				callbakcFun(response.data,items);
					}
	    		);
	    }
		//replacement  
		var replacement = function(items,closeFun) {
	    	$http.get('/rereplay?id='+items[0].id, null)
	    	.then(
	    			function successCallback(response) {
	    				toastr["success"]('完成置换成功');
	    				closeFun();
	    				items[1](items,items[2]);
	    				
	    				$timeout(function() {
	    					$('body').addClass('modal-open')
	    				})
					}
	    		);
	    }
		var getOrderAttachment = function(id,callbakcFun) {
	    	$http.get('/attachment?id='+id, null)
	    	.then(
	    			function successCallback(response) {
	    				callbakcFun(response.data);
					}
	    		);
	    }
		var getOrder = function(items, callbakcFun) {
	    	$http.get('/addcommodity/' + items[0].id, null)
	    	.then(
	    			function successCallback(response) {
	    				callbakcFun(response.data);
	    				items[2](items[1].page,items[1].pageSize,items[1].sort,items[1].filter,items[3])
	    				//(page, pageSize, sort, filter, callbackFun)
	    		//	$ctrl.items = [{'id':id,'type':flag}, {'page': page, 'pageSize':pageSize, 'sort':sort, 'filter':filter}, service.getPage, getPageCallbackFun];
					}
	    		);
	    }
		var getOfficial=function(id,callbakcFun){
		   $http.get('/offical?id=' + id, null)
	    	.then(
    			function successCallback(response) {
    				callbakcFun(response.data);
				}
    		);
		}
		var getGadget=function(id,callbakcFun){
			   $http.get('/getGadget?id=' + id, null)
		    	.then(
	    			function successCallback(response) {
	    				callbakcFun(response.data);
					}
	    		);
			}
	   var getApplyQualify=function(id,callbakcFun){
		   $http.get('/applyQualify/list?id=' + id, null)
	    	.then(
    			function successCallback(response) {
    				callbakcFun(response.data);
				}
    		);
		}
		
*/

        //获取所属机构

		return {
			saveCommodity:saveCommodity,
			/*getPage : getPage,
			getOfficial:getOfficial,
			getApplyQualify:getApplyQualify,
			getGadget:getGadget,
			addOrder : addOrder,
			getOrder : getOrder,
			getOrderCollection:getOrderCollection,
			getOrderAttachment:getOrderAttachment,
			replacement:replacement,*/
		}
	}]);

