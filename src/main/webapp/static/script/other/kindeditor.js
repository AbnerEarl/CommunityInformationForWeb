(function($) {
	
    window['kingEditorFileParams'] = {
		//显示浏览远程服务器按钮
		allowFileManager : false,
		//不格式化文件地址
		formatUploadUrl : false,
		//指定上传文件参数名称
		filePostName  : "uploadFile",
		//指定上传文件的服务器端程序
		uploadJson : '/file/upload',
		//上传类型，分别为image、flash、media、file
		dir : "file"
    };
    
    window['kingEditorPicParams'] = {
		//显示浏览远程服务器按钮
		allowFileManager : false,
		//不格式化文件地址
		formatUploadUrl : false,
		//指定上传文件参数名称
		filePostName  : "uploadFile",
		//指定上传文件的服务器端程序
		uploadJson : '/file/upload',
		//上传类型，分别为image、flash、media、file
		dir : "image"
    };
	
	$.fn.createEditor = function(options) {
        var settings = $.extend({
			allowFileManager : true,
			afterBlur: function(){
				this.sync();
			}
        }, kingEditorPicParams, options);
		return KindEditor.create(this, settings);
	}
	
	$.fn.initOnePicUpload = function(options) {
    	this.click(function(){
			var _self = $(this);
			KindEditor.editor(kingEditorPicParams).loadPlugin('image', function() {
				var editor = this;
		        var settings = $.extend({
					clickFn: function(url, title, width, height, border, align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.before("<img src='" + url + "' width='80' height='80'/>");
						editor.hideDialog();
					}
				}, options);
				editor.plugin.imageDialog(settings);
			});
		});
	}
	
	$.fn.initPicUpload = function(data) {
		this.each(function(i,e){
			var _ele = $(e);
			_ele.siblings("div.pics").remove();
    		_ele.siblings("div.pics").remove();
    		_ele.after('\<div class="pics">\<ul></ul>\</div>');
			_ele.click(function(){
				KindEditor.editor(kingEditorPicParams).loadPlugin('multiimage', function() {
	    			var editor = this;
	    			editor.plugin.multiImageDialog({
						clickFn : function(urlList) {
							var imgArray = [];
							KindEditor.each(urlList, function(i, data) {
								imgArray.push(data.url);
								_ele.parent().find(".pics ul").append("<li style='display:inline;margin-right:7px;'><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='80' /></a></li>");
							});
							_ele.parent().find("input").val(imgArray.join(","));
							editor.hideDialog();
						}
					});
				});
			});
		});
	}
	
	$.fn.fileManager = function(options) {
    	this.click(function(){
			KindEditor.editor(kingEditorFileParams).loadPlugin('filemanager', function() {
				var editor = this;
		        var settings = $.extend({
					viewType: 'VIEW',
					dirName: 'image',
					clickFn: function(url, title) {
						_self.siblings("input").val(url);
						editor.hideDialog();
					}
				}, options);
				editor.plugin.filemanagerDialog(settings);
			});
		});
	}
	
	$.fn.insertFile = function(options) {
    	this.click(function(){
			var _self = $(this);
			KindEditor.editor(kingEditorFileParams).loadPlugin('insertfile', function() {
				var editor = this;
		        var settings = $.extend({
					fileUrl : _self.siblings("input").val(),
					clickFn : function(url, title) {
						_self.siblings("input").val(url);
						editor.hideDialog();
					}
				}, options);
				editor.plugin.fileDialog(settings);
			});
		});
	}
	
})(jQuery);