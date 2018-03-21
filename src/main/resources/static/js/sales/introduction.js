$(function () {
	//LikBasket  加入购物车
	$("#LikBasket").on("click",function(){
		var commodityNum=$("#commodityNum").val()
		var buyNum= $("input[name=buyNum]").val()
		$("form").submit(function(){
			$.ajax({
				    url: "addToShoppingCart",
				    type: "post",
				    method: "post",
				    dataType: "json",
				    data: {
				    	commodityNum:commodityNum,
				    	buyNum:buyNum
				    },
				    success: function (msg) {
				    	if(msg=="error"){
				    		console.info("error");
				    	}else{
				    		console.info("success");
				    	}
				    }
			});
			 return false;
		});
		
	});
	
	//$(".pagination a").on("click", function() {
		  //   var page = $(this).text();
		  //   if (!isNaN(page)) {
		  //   } else if ("«"==page) {
		  //     var now = $("ul.pagination li.active a").text();
		  //     page = (now>1)?(now-1):1;
		  //   } else {
		  //     var now = $("ul.pagination li.active a").text();
		  //     page = (now==page_count)?page_count:(parseInt(now)+1);
		  //   }
		  //   initScoresList(type, page, search);
		  // });
	/*$("balabala").on("click", function() {
	   $("form").attr("action", "balabala")
	   })*/
	
})

/*$(form).submit(function() {
    $.ajax({
         balabala
    });
    return false;
});*/
/*
function addToshoppingCart(){
	var commodityNum=
}*/