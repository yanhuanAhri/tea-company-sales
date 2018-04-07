	var app = angular.module("searchApp", ['ngMessages']);
	app.controller('searchCtrl', [ '$scope', '$http', '$rootScope','searchService', function($scope,  $http, $rootScope,service) {
	
		//全局变量
		$scope.pageNumber=[]
		$scope.tipNum=1;
		$scope.productType=null;
		$scope.pickYear=null;
		$scope.purpose=null;
		$scope. productType=[
			{
				'productType':'乌龙茶',
				'teaName':[{'value':'安溪铁观音'},{'value':'武夷岩茶'},{'value':'台湾高山茶'},{'value':'广东单枞'},
						{'value':'漳平水仙'},{'value':'其他乌龙茶'}]
			},
			{
				'productType':'红茶',
				'teaName':[{'value':'金骏眉'},{'value':'正山小种'},{'value':'祁门红茶'},{'value':'云南滇红'},
						{'value':'凌云白毫红茶'},{'value':'英德红茶'},{'value':'锡兰红茶'},{'value':'其他红茶'}]
			},
			{
				'productType':'绿茶',
				'teaName':[{'value':'龙井'},{'value':'碧螺春'},{'value':'黄山毛峰'},{'value':'六安瓜片'},{'value':'太平猴魁'},
						{'value':'安吉白茶'},{'value':'信阳毛尖'},{'value':'云南滇绿'},{'value':'凌云白毫绿茶'},{'value':'四川绿茶'}]
			},
			{
				'productType':'黑茶',
				'teaName':[{'value':'普洱茶'},{'value':'安化黑茶'},{'value':'柑普茶'}]
			},
			{
				'productType':'白茶',
				'teaName':[{'value':'福鼎白茶'},{'value':'其他白茶'}]
			},
			{
				'productType':'花茶',
				'teaName':[{'value':'茉莉花茶'},{'value':'玫瑰花茶'},{'value':'菊花茶'},{'value':'其他花茶'}]
			},
			{
				'productType':'茶器',
				'teaName':[{'value':'陶瓷茶具'},{'value':'紫砂茶具'},{'value':'玻璃茶具'},{'value':'茶盘'},
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
		$scope.search=$("#firstSearch").val();
		service.searchTea($scope.search,null,searchTeaCallback);
		
		service.getShoppingCartCount(function(data){
			if(data.code==1){
				$scope.shopCartCount=data.count;
			}else{
				$scope.shopCartCount='';
			}
		});
		service.getTeaSet(function(data){
			$scope.teaSet=data.teaSet;
		});
	}]);


	app.factory('searchService', [ '$q', '$http','$timeout', function ($q, $http,$timeout) {
		
	
		var searchTea=function(search,data,searchTeaCallback){
			$http.post('searchTea?search='+search,angular.toJson(data)).then(
	    			function (response) {
	    				searchTeaCallback(response.data);
				});
		}
		var getShoppingCartCount=function(callback){
			$http.get('shoppingCartCount').then(
	    			function (response) {
	    				callback(response.data);
				});
		}
		var getTeaSet=function(getTeaSetCallbackFun){
			$http.get('teaSet').then(
	    			function (response) {
	    				getTeaSetCallbackFun(response.data);
				});
		}
		return {
			getTeaSet:getTeaSet,
			searchTea:searchTea,
			getShoppingCartCount:getShoppingCartCount,
			
		}
	}]);