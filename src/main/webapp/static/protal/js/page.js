(function($) {
	var ms = {
		init: function(obj, args) {
			return(function() {
				ms.fillHtml(obj, args);
				ms.bindEvent(obj, args);
			})();
		},
		//填充html 
		fillHtml: function(obj, args) {
			return(function() {
				obj.empty();
				//上一页 
				if(args.current > 1) {
					obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
				} else {
					obj.remove('.prevPage');
					obj.append('<span class="disabled">上一页</span>');
				}
				//中间页码 
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4) {
					obj.append('<a href="javascript:;" class="tcdNumber">' + 1 + '</a>');
				}
				if(args.current - 2 > 2 && args.current <= args.pageCount && args.pageCount > 5) {
					obj.append('<span>...</span>');
				}
				var start = args.current - 2,
					end = args.current + 2;
				if((start > 1 && args.current < 4) || args.current == 1) {
					end++;
				}
				if(args.current > args.pageCount - 4 && args.current >= args.pageCount) {
					start--;
				}
				for(; start <= end; start++) {
					if(start <= args.pageCount && start >= 1) {
						if(start != args.current) {
							obj.append('<a href="javascript:;" class="tcdNumber">' + start + '</a>');
						} else {
							obj.append('<span class="current">' + start + '</span>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5) {
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount - 2 && args.pageCount != 4) {
					obj.append('<a href="javascript:;" class="tcdNumber">' + args.pageCount + '</a>');
				}
				//下一页 
				if(args.current < args.pageCount) {
					obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
				} else {
					obj.remove('.nextPage');
					obj.append('<span class="disabled">下一页</span>');
				}
			})();
		},
		//绑定事件 
		bindEvent: function(obj, args) {
			return(function() {
				obj.on("click", "a.tcdNumber", function() {
					var current = parseInt($(this).text());
					ms.fillHtml(obj, {
						"current": current,
						"pageCount": args.pageCount
					});
					if(typeof(args.backFn) == "function") {
						args.backFn(current);
					}
				});
				//上一页 
				obj.on("click", "a.prevPage", function() {
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj, {
						"current": current - 1,
						"pageCount": args.pageCount
					});
					if(typeof(args.backFn) == "function") {
						args.backFn(current - 1);
					}
				});
				//下一页 
				obj.on("click", "a.nextPage", function() {
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj, {
						"current": current + 1,
						"pageCount": args.pageCount
					});
					if(typeof(args.backFn) == "function") {
						args.backFn(current + 1);
					}
				});
			})();
		},
		setPage: function(obj, options){
			var args = $.extend({
				pageCount: options.pageCount,
				current: options.page,
				backFn: function() {
			    	$.get(options.url, {page: options.page, rows: options.rows}, function(data) {
			    		options.success(data.rows);
			    	}, 'json'); 
				}
			}, options);
			ms.init(obj, args);
		}
	}
	
	$.fn.createPage = function(url, success, pageSize) {
	    if(url.length == 0) throw "url request is required";
	    var page = 1;
	    var rows = pageSize ? pageSize : 20;
	    var that = this;
		$.get(url, {page: page, rows: rows}, function(data) {
			success(data.rows);
			var pageCount = (data.total % rows == 0)?parseInt(data.total/rows):(parseInt(data.total/rows)+1);
	        var options = {
	        	url : url,
	        	page : page,
	        	rows : rows,
	        	pageCount : pageCount,
	        	success : success
	        };
			ms.setPage(that, options);
		}, 'json');
	}
	
})(jQuery);