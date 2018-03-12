/**
 * 
 */
(function() {
	
	var categoryId = -1;
	var pageSize = 15;
	function ProductImageDialog(editor) {

		var getTree = function(callbackFn) {

			$.ajax({
				type : "get",
				url : "imgs/menus/all",
				data : null,
				success : function(data) {
					callbackFn(data);
				}
			});
		};
		
		var getPageTotal = function() {
			var url = "imgs/queryCount?categoryId=" + categoryId;
			$.ajax({
				type : "get",
				url : url,
				data : null,
				success : function(data) {
					if(data.total > 0) {
						getPage(1);
					} else {
						$("#productImageList_publish").html('');
						showPagatoin(null);
					}
				}
			});
		};
		
		var getPage = function(page) {
			var url = "imgs/list?categoryId=" + categoryId + "&page=" + page + "&pageSize=" + pageSize;
			$.ajax({
				type : "get",
				url : url,
				data : null,
				success : function(data) {
					showImage(data.result, data.rootPath);
					showPagatoin(data.page);
				}
			});
		};
		
		var showImage = function(data, rootPath) {
			var html = '<div class="proimgslist">';
			$.each(data, function(index, obj){
				html += '<div class="thumbnail"><img src="'+rootPath + obj.path+'"><div class="caption"><h3>'+obj.name+'</h3><i class="fa fa-check"></i></div></div>';
			});
			html += "</div>";
			
			$("#productImageList_publish").html(html);
			$(".proimgslist .thumbnail").click(function(){
				//var $obj = $($event.target).parent();
				if($(this).hasClass("on")){
					$(this).removeClass("on");
				} else {
					$(this).addClass("on");
				}
			})
			
		}
	
		var showPagatoin = function(page) {
			if(page==null) {
				page = {totalPages:0, currentPage: 0}
			}
			var pageHtml = '<div id="ckedit-publish-image-element-pagination-row" class="col-md-12">';
			pageHtml += '<ul id="ckedit-publish-image-element-pagination" style="cursor:hand;"></ul>';
			pageHtml += '<ul class="pagination proimgpagination">';
			pageHtml += '<li><a href="javascript:void(0);">共'+page.totalPages+'页</a></li>';
			pageHtml += '<li><a href="javascript:void(0);"><input type="text"/><span onClick="goPage()">GO</span></a></li>';
			pageHtml += '</ul></div>';
			$("#ckedit-publish-image-element-pagination-row").html(pageHtml);
			
			var element = $('#ckedit-publish-image-element-pagination');
			if(page.totalPages > 1){
	    	    //初始化所需数据
		        var options = {
		        	bootstrapMajorVersion:3,
		        	currentPage: page.currentPage, //当前页数
		            numberOfPages: page.pageSize, //显示页码数标个数
		            totalPages: page.totalPages, //总共的数据所需要的总页数
		            itemTexts: function (type, page, current) {//图标的更改显示可以在这里修改。
		            switch (type) {  
		                    case "first":  
		                        return "<<";  
		                    case "prev":  
		                        return "<";  
		                    case "next":  
		                        return ">";  
		                    case "last":  
		                        return ">>";  
		                    case "page":  
		                        return  page;  
		                }                 
		            }, 
		            tooltipTitles: function (type, page, current) {
						//如果想要去掉页码数字上面的预览功能，则在此操作。例如：可以直接return。
		                switch (type) {
				            case "first":
				                return "Go to first page";
				            case "prev":
				                return "Go to previous page";
				            case "next":
				                return "Go to next page";
				            case "last":
				                return "Go to last page";
				            case "page":
				                return (page === current) ? "Current page is " + page : "Go to page " + page;
				        }
		            },
		            onPageClicked: function (e, originalEvent, type, page) {
		            	getPage(page);
		            }
		        };
		        element.bootstrapPaginator(options);
    		}else{
    			element.empty();
    		}
		};

		var heigth = $(window).height() * 0.8;
		var width = $(window).width() * 0.8;

		if($("#productImageCategoryTree1").length > 0 ) {
			$("#productImageCategoryTree1").remove();
		}
		if($("#productImageList_publish").length > 0 ) {
			$("#productImageList_publish").remove();
		}
		if($("#ckedit-publish-image-element-pagination-row").length > 0 ) {
			$("#ckedit-publish-image-element-pagination-row").remove();
		}
		
		return {
			title : '图片选择',
			resizable : CKEDITOR.DIALOG_RESIZE_BOTH,
			minWidth : width,
			minHeight : heigth,
			buttons : [ CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ],
			contents : [ {
				id : "ckedit-product-image-info",
				label : 'Test',
				elements : [ {
					type : 'hbox',
					widths : [ '20%', '80%' ],
					padding : 2,
					children : [ {
						type : "html",
						html : "<div id=\"productImageCategoryTree1\" class=\"thistreenewcss\"></div>"
					}, {
						type : "vbox",
						padding : 2,
						heights : [ heigth*0.9, heigth*0.1],
						children : [{
							type:'html',
							html:'<div id="productImageList_publish"></div>'
						}, {
							type: 'html',
							html:'<div id="ckedit-publish-image-element-pagination-row" class="col-md-12"></div>'
						}] 
					} ]
				} ]
			} ],
			onLoad : function() {
				
			},
			onShow : function() {
				var $this = this;
				var showTree = function(data) {
					var $expandibleTree = $('#productImageCategoryTree1').treeview({
						data : data,
						levels: 1,
						onNodeSelected : function(event, node) {
							categoryId = node.id;
							getPageTotal();
							
							if(!node.state.expanded) {
								$expandibleTree.treeview('expandNode', [ node.nodeId, { levels: 1, silent: true } ]);
							} else {
								$expandibleTree.treeview('collapseNode', [ node.nodeId, { levels: 1, silent: true } ]);
							}
							
						},
						onNodeUnselected : function(event, node) {
							/*if(!node.state.expanded) {
								$expandibleTree.treeview('expandNode', [ node.nodeId, { levels: 1, silent: true } ]);
							} else {
								$expandibleTree.treeview('collapseNode', [ node.nodeId, { levels: 1, silent: true } ]);
							}*/
						}
					});
				}
				getTree(showTree);
				
			},
			onHide : function() {
				
			},
			onOk : function() {
				var selImgs = $("#productImageList_publish").find(".on");
				if(selImgs == null || selImgs.length ==0) {
					toastr["warning"]("请选择图片");
					return false;
				}
				
				var html = '';
				$.each(selImgs, function(index, obj) {
					var img = $(selImgs[index]).find("img")[0];
					html += '<div><img src="'+img.src+'" /></div>';
				});
				
                var element = new CKEDITOR.dom.element('span', editor.document);
                element.appendHtml(html);
                editor.insertElement(element);
                
                //remove all selected image
                $.each(selImgs, function(index, obj) {
					$(obj).removeClass("on");
				});
                
				this.commitContent(editor);
			},
			onCancel : function() {
				//remove all selected image
				var selImgs = $("#productImageList_publish").find(".on");
				if(selImgs != null || selImgs.length > 0) {
					$.each(selImgs, function(index, obj) {
						$(obj).removeClass("on");
					});
				}
			}
		};
	}

	CKEDITOR.dialog.add('productimage', function(editor) {
		return ProductImageDialog(editor);
	});
})()