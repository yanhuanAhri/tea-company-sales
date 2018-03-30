	var app = angular.module("searchApp", ['ngMessages']);
	app.controller('searchCtrl', [ '$scope', '$http', '$rootScope','searchService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		$scope.pageNumber=[]
		$scope.tipNum=1;
		$scope.productType=null;
		$scope.pickYear=null;
		$scope.purpose=null;
		$scope.teaSort=[
				{
					'teaName':'乌龙茶',
					'productType':[{'value':'安溪铁观音'},{'value':'武夷岩茶'},{'value':'台湾高山茶'},{'value':'广东单枞'},
							{'value':'漳平水仙'},{'value':'其他乌龙茶'}]
				},
				{
					'teaName':'红茶',
					'productType':[{'value':'金骏眉'},{'value':'正山小种'},{'value':'祁门红茶'},{'value':'云南滇红'},
							{'value':'凌云白毫红茶'},{'value':'英德红茶'},{'value':'锡兰红茶'},{'value':'其他红茶'}]
				},
				{
					'teaName':'绿茶',
					'productType':[{'value':'龙井'},{'value':'碧螺春'},{'value':'黄山毛峰'},{'value':'六安瓜片'},{'value':'太平猴魁'},
							{'value':'安吉白茶'},{'value':'信阳毛尖'},{'value':'云南滇绿'},{'value':'凌云白毫绿茶'},{'value':'四川绿茶'}]
				},
				{
					'teaName':'黑茶',
					'productType':[{'value':'普洱茶'},{'value':'安化黑茶'},{'value':'柑普茶'}]
				},
				{
					'teaName':'白茶',
					'productType':[{'value':'福鼎白茶'},{'value':'其他白茶'}]
				},
				{
					'teaName':'花茶',
					'productType':[{'value':'茉莉花茶'},{'value':'玫瑰花茶'},{'value':'菊花茶'},{'value':'其他花茶'}]
				},
				{
					'teaName':'茶器',
					'productType':[{'value':'陶瓷茶具'},{'value':'紫砂茶具'},{'value':'玻璃茶具'},{'value':'茶盘'},
							{'value':'建盏'},{'value':'茶道配件'},{'value':'铁艺'}]
				}
			];
		
		var filterData=function(){
			var productType=$("#select1").find(".selected").children().text();
			var pickYear=$("#select3").find(".selected").children().text();
			var purpose=$("#select2").find(".selected").children().text();
			$scope.productType=(productType=='全部'? null:productType);
			$scope.pickYear=(pickYear=='全部'? null:pickYear);
			$scope.purpose=(purpose=='全部'? null:purpose);
		}
		$scope.searchClick=function(){
			filterData();
			var data={
					//搜素数据组建
					'pageNum':'1',
					'productType':$scope.productType,
					'pickYear':$scope.pickYear,
					'purpose':$scope.purpose,
					
			}
			var search=$("#searchInput").val();
			service.searchTea(search,data,searchTeaCallback)
		}
		
		$scope.filterClick=function(){
			filterData();
			var data={
					//搜素数据组建
					'pageNum':$scope.tipNum,
					'productType':$scope.productType,
					'pickYear':$scope.pickYear,
					'purpose':$scope.purpose,
					
			}
			service.searchTea($scope.search,data,searchTeaCallback)
		}
		
		$scope.pageNum=function(num,symbol){
			if(num!==''){
				$scope.tipNum=num;
			}else if(symbol!=''){
				if((symbol=='-' && $scope.tipNum<='1') || (symbol=='+' && $scope.tipNum>=$scope.pageNumber.length)){
					return;
				}
				$scope.tipNum=$scope.tipNum+parseInt(symbol+'1');
			}
			filterData();
			//data数据组建
			var data={
					//搜素数据组建
					'pageNum':$scope.tipNum,
					'productType':$scope.productType,
					'pickYear':$scope.pickYear,
					'purpose':$scope.purpose,
					
			}
			service.searchTea($scope.search,data,searchTeaCallback)
		}
		
		var searchTeaCallback= function(data) {
			if(data.code!=1){
				window.location.href = "/login.html/"
			}else{
				$scope.count=data.count;
				$scope.search=data.search;
				var pageNumber=parseInt(parseInt($scope.count)/12);
				if(parseInt($scope.count)%12>0){
					pageNumber=pageNumber+1;
				}
				$scope.pageNumber=[];
				for(var i=1;i<=pageNumber;i++){
					$scope.pageNumber.push(i);
				}
				if($scope.tipNum>pageNumber){
					$scope.tipNum=1;
				}
				$scope.commodityList=data.commodityVoList;
			}
		}
		$scope.search=$("#firstSearch").val();
		service.searchTea($scope.search,null,searchTeaCallback)
	}]);


	app.factory('searchService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
		/*var getAllReceiving=function(callbackFun){
			$http.get('findUserReceiving').then(
	    			function (response) {
						callbackFun(response.data);
			});
		};*/
		var searchTea=function(search,data,searchTeaCallback){
			$http.post('searchTea?search='+search,angular.toJson(data)).then(
	    			function (response) {
	    				searchTeaCallback(response.data);
				});
		}
		return {
			searchTea:searchTea,
		/*	getReceivingById:getReceivingById,*/
			
		}
	}]);