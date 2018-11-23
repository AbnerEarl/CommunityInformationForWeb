(function($) {
	
	$.fn.initSelectDialog = function(url, options) {
		$(this).each(function(i,e){
    		var _ele = $(e);
    		_ele.unbind('click').click(function(){
    			var setting = $.extend({
					width: "300",
    			    height: "450",
    			    modal: true,
    			    closed: true,
    			    title: "请选择数据",
    			    onOpen: function(){
    			    	var _win = this;
    			    	$("ul", _win).tree({
    			    		url: url,
    			    		animate: true,
    			    		onClick: function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				_ele.prev().val(node.id);
    			    				_ele.next().text(node.text);
    			    				$(_win).window('close');
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose: function(){
    			    	$(this).dialog("destroy");
    			    }
				}, options);
    			$("<div>").append("<ul>").dialog(setting).dialog('open');
    		});
    	});
	}
	
})(jQuery);

































