(function($) { 
	var text;
	var value;
	var type;
	var selected;
	var keep;

	$.fn.FillOptions = function(url,options){
	    if(url.length == 0) throw "request is required";
	    text = options.textfield ? options.textfield : "text";
	    value = options.valuefiled ? options.valuefiled : "value";    
	    type = options.datatype ? options.datatype.toLowerCase() : "json";
	    if(type != "xml") type = "json";
	    keep = options.keepold ? options.keepold : false;
	    selected = options.selectedindex || 0;
	    $.ajaxSetup({
	    	async:false
	    });
	    var datas;
	    if(type == "xml"){
	        $.get(url,function(xml){
	        	datas=xml;
	        });
	    }else {
	        $.getJSON(url,function(json){
	        	datas=json;
	        });
	    }
	    if(datas == undefined){
			return;
		}
	    this.each(function(){
	        if(this.tagName == "SELECT"){
	            var select = this;
	            if(!keep) $(select).html("");
	            addOptions(select, datas);
	        }
	    });
	}
	
	$.fn.CascadingSelect = function (target, url, options, endfn) {
	    $.ajaxSetup({ async: false });
	    if (target[0].tagName != "SELECT") throw "target must be SELECT";
	    if (url.length == 0) throw "request is required";
	    if (options.parameter == undefined) throw "parameter is required";
	    this.change(function() {
	        var newurl = "";
	        var urlstr = url.split("?");
	        newurl = urlstr[0] + "?" + options.parameter + "=" + encodeURIComponent($(this).val()) + "&" + urlstr[1];
	        target.FillOptions(newurl, options);
	        if (typeof endfn == "function") endfn();
	    });
	}
	
	$.fn.AddOption = function (text, value, selected, index) {
	    var option = new Option(text, value);
	    this[0].options.add(option, index);
	    this[0].options[index].selected = selected;
	}

	function addOptions(select, datas){        
	    var options;
	    var datas;
	    if(type == "xml"){
	        $(text, datas).each(function(i){            
	            option = new Option($(this).text(), $($(value,datas)[i]).text());
	            if(i == selected) option.selected = true;
	            select.options.add(option);
	        });
	    }else {
	        $.each(datas, function(i, n){
	            option = new Option(eval("n."+text), eval("n."+value));
	            if(i == selected) option.selected = true;
	            select.options.add(option);
	        });
	    }
	}
	
})(jQuery);