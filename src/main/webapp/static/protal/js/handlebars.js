Handlebars.registerHelper('equal', function(v1, v2, options) {
    if(v1 === v2) {
        return options.fn(this)
    } else {
        return options.inverse(this)
    }
});

Handlebars.registerHelper('listFirst', function(value, options) {
	return value.split(",")[0];
});

var handlebars = {
	loadTemplate: function(el, templateId, data) {
		$(el).html("");
	    var tpl = $(templateId).html();
    	var handle = Handlebars.compile(tpl);
		var html = handle(data);
		$(el).append(html);
	}
}