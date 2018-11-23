(function($){
	
	$.fn.fSelect = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.fSelect.methods[options];
			if (method){
				return method(this, param);
			} else {
				return;
			}
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'fSelect');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'fSelect', {
					options: $.extend({}, $.fn.fSelect.defaults, options),
					data: []
				});
			}
			create(this);
			if (state.options.data){
				loadData(this, state.options.data);
			}
			request(this);
		});
	};
	
	$.fn.fSelect.methods = {
		options: function(jq){
			return $.data(jq[0], 'fSelect').options;
		},
		getData: function(jq){
			return $.data(jq[0], 'fSelect').data;
		},
		getText: function (jq) {
            return getText(jq[0]);
        },
        setText: function (jq, text) {
            return jq.each(function () {
                setText(this, text);
            });
        },
		getValues: function (jq) {
            return getValues(jq[0]);
        },
        getValue: function (jq) {
            return getValue(jq[0]);
        },
		setValues: function(jq, values){
			return jq.each(function(){
				setValues(this, values);
			});
		},
		setValue: function(jq, value){
			return jq.each(function(){
				setValues(this, $.isArray(value) ? value : [value]);
			});
		},
		clear: function(jq){
			return jq.each(function(){
				setValues(this, []);
			});
		},
		loadData: function(jq, data){
			return jq.each(function(){
				loadData(this, data);
			});
		},
		reload: function(jq, url){
			return jq.each(function(){
				if (typeof url == 'string'){
					request(this, url);
				} else {
					if (url){
						var opts = $(this).fSelect('options');
						opts.queryParams = url;
					}
					request(this);
				}
			});
		}
	};
	
	$.fn.fSelect.defaults = $.extend({}, {
        placeholder: '请选择', 
        numDisplayed: 3,
        groupField: null,
		groupFormatter: function(group){
			return group;
		},
        overflowText: '已选择{n}条数据', 
        searchText: 'Search',
        multiple: false,
        showSearch: true,
        url: null,
        data: null,
		method: 'get',
        textField: 'text',
        valueField: 'value',
        keepLocalData: true,
		queryParams: {},
		loadFilter: function(data){
			return data;
		},
		formatter: function(row){
			var opts = $(this).fSelect('options');
			return row[opts.textField];
		},
		loader: function(param, success, error){
			var opts = $(this).fSelect('options');
			if (!opts.url) return false;
			$.ajax({
				type: opts.method,
				url: opts.url,
				data: param,
				dataType: 'json',
				success: function(data){
					success(data);
				},
				error: function(){
					error.apply(this, arguments);
				}
			});
		},
		onBeforeLoad: function(param){},
		onLoadSuccess: function(){},
		onLoadError: function(){},
		onShowPanel: function () {},
		onHidePanel: function () {},
		onChange: function (newValue, oldValue) {}
	});
	
    window.fSelect = {
        'active': null,
        'idx': -1,
        'selectedList': []
    };
    
    function setIndexes($wrap) {
        $wrap.find('.fs-option:not(.hidden)').each(function(i, el) {
            $(el).attr('data-index', i);
            $wrap.find('.fs-option').removeClass('hl');
        });
        $wrap.find('.fs-check input').focus();
        window.fSelect.idx = -1;
    }
    
    function setScroll($wrap) {
        var $container = $wrap.find('.fs-options');
        var $selected = $wrap.find('.fs-option.hl');
        var itemMin = $selected.offset().top + $container.scrollTop();
        var itemMax = itemMin + $selected.outerHeight();
        var containerMin = $container.offset().top + $container.scrollTop();
        var containerMax = containerMin + $container.outerHeight();
        if (itemMax > containerMax) { 
            var to = $container.scrollTop() + itemMax - containerMax;
            $container.scrollTop(to);
        }else if (itemMin < containerMin) { 
            var to = $container.scrollTop() - containerMin - itemMin;
            $container.scrollTop(to);
        }
    }
    
    function setValues(target, values){
        var $wrap = $(target).closest('.fs-wrap');
        if(values != null){
			$wrap.find('select').data('values', $.isArray(values) ? values : [values]);
        }else {
        	$wrap.find('select').data('values', []);
        }
    }
    
    function getValues(target){
   		var $wrap = $(target).closest('.fs-wrap');
        var values = $wrap.find('select').data('values');
        return values;
    }
    
    function getValue(target){
    	var values = getValues(target);
    	return values.length <= 0 ? null : values[0];
    }
    
    function setText(target, text){
        var wrap = $(target).closest('.fs-wrap');
		var state = $.data(wrap.find('select')[0], 'fSelect');
		var opts = state.options;
    	text = text || opts.placeholder;
	    var labelText = [];
	    wrap.find('.fs-option.selected').each(function(i, el) {
	        labelText.push($(el).find('.fs-option-label').text());
	    });
	    if (labelText.length < 1) {
	        labelText = text;
	    }else if (labelText.length > opts.numDisplayed) {
	        labelText = opts.overflowText.replace('{n}', labelText.length);
	    }else {
	        labelText = labelText.join(', ');
	    }
	    wrap.find('.fs-label').html(labelText);
	    $(target).change();
    }
    
    function getText(target){
    	var $wrap = $(target).closest('.fs-wrap');
    	return $wrap.find('.fs-label').html();
    }
	
	function create(target){
		var state = $.data(target, 'fSelect');
		var opts = state.options;
        var multiple = opts.multiple ? ' multiple' : '';
        $(target).wrap('<div class="fs-wrap' + multiple + '"></div>');
        $(target).before('<div class="fs-label-wrap"><div class="fs-label">' + opts.placeholder + '</div><span class="fs-arrow"></span></div>');
        $(target).before('<div class="fs-dropdown hidden"><div class="fs-options"></div></div>');
        $(target).addClass('hidden');
        var wrap = $(target).closest('.fs-wrap');
        if (opts.multiple) {
            var search = '<div class="fs-check"><input type="checkbox" class="fs-check-all">全选&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" class="fs-check-invert">反选</div>';
            wrap.find('.fs-dropdown').prepend(search);
        }
        if (opts.showSearch) {
            var search = '<div class="fs-search"><input type="search" placeholder="' + opts.searchText + '" /></div>';
            wrap.find('.fs-dropdown').prepend(search);
        }
	}
	
	function loadData(target, data){
		var state = $.data(target, 'fSelect');
		var opts = state.options;
		state.data = opts.loadFilter.call(target, data);
        defaultView(target, $(target).closest('.fs-wrap'), state.data);
        var values = [];
        $.each(data, function(i, row) {
        	if(row['selected']){
        		values.push(row[opts.valueField]);
        	}
        });
		if (opts.multiple){
			setValues(target, values);
		} else {
			setValues(target, values.length ? [values[values.length-1]] : null);
		}
		setText(target);
        opts.onLoadSuccess.call(target, data);
	} 
	 
	function request(target, url, param){
		var opts = $.data(target, 'fSelect').options;
		if (url){
			opts.url = url;
		}
		param = $.extend({}, opts.queryParams, param || {});
		if (opts.onBeforeLoad.call(target, param) == false){
			return;
		} 
		var flag = opts.loader.call(target, param, function(data){
			if(opts.keepLocalData){
				var localData = parseData(target);
				if (localData.length){
					$.each(localData, function(i, item) {
						data.push(item);
					});
				}
			}
			loadData(target, data);
		}, function(){
			opts.onLoadError.apply(this, arguments);
		});
		if(flag == false){
			if(opts.keepLocalData){
				var localData = parseData(target);
				loadData(target, localData);	
			}
		}
	}
	
	function parseData(target){
		var data = [];
		var opts = $(target).fSelect('options');
		$(target).children().each(function(i, el){
			var $el = $(el);
			if ('optgroup' == $el.prop('nodeName').toLowerCase()){
				var group = $el.prop('label');
				$el.children().each(function(){
					_parseItem(el, group);
				});
			} else {
				_parseItem(el);
			}
		});
		return data;
		
		function _parseItem(el, group){
			var t = $(el);
			var row = {};
			row[opts.valueField] = t.prop('value') != undefined ? t.prop('value') : t.text();
			row[opts.textField] = t.text();
			row['selected'] = t.is(':selected');
			row['disabled'] = t.is(':disabled');
			if (group){ 
				opts.groupField = opts.groupField || 'group';
				row[opts.groupField] = group;
			}
			data.push(row);
		}
	};
	
	function defaultView(target, container, data){
		var state = $.data(target, 'fSelect');
		var opts = state.options;
		state.groups = [];
		var dd = [];
		var group = undefined;
		for(var i=0; i < data.length; i++){
			var row = data[i];
			var v = row[opts.valueField] + '';
			var s = row[opts.textField];
			var g = row[opts.groupField];
			if (g){
				if (group != g){
					group = g;
					state.groups.push({
						value: g,
						startIndex: i,
						count: 1
					});
					dd.push('<div class="fs-optgroup-label">');
					dd.push(opts.groupFormatter ? opts.groupFormatter.call(target, g) : g);
					dd.push('</div>');
				} else {
					state.groups[state.groups.length-1].count++;
				}
			} else {
				group = undefined;
			}
			var selected = row.selected ? ' selected' : '';
			dd.push('<div class="fs-option' + selected + '" data-value="' + v + '"><span class="fs-checkbox"><i></i></span><div class="fs-option-label">');
			dd.push(opts.formatter ? opts.formatter.call(target, row) : s);
			dd.push('</div></div>');
			container.find('.fs-options').html(dd.join(''));	
		}
	}
	
    $(document).on('click', '.fs-option', function() {
        var $wrap = $(this).closest('.fs-wrap');
        var selected;
        if ($wrap.hasClass('multiple')) {
        	selected = [];
            $(this).toggleClass('selected');
            $wrap.find('.fs-option.selected').each(function(i, el) {
                selected.push($(el).attr('data-value'));
            });
            $wrap.find(".fs-check-all").prop("checked",$wrap.find('.fs-option:not(.hidden)').length == $wrap.find('.fs-option.selected').length ? true : false);
        }else {
        	selected = $(this).attr('data-value')
            $wrap.find('.fs-option').removeClass('selected');
            $(this).addClass('selected');
            $wrap.find('.fs-dropdown').addClass('hidden');
        }
        setValues(this, selected);
        setText(this);
    });
    
    $(document).on('keyup', '.fs-search input', function(e) {
        if (40 == e.which) {
            $(this).blur();
            return;
        }
        var $wrap = $(this).closest('.fs-wrap');
        var keywords = $(this).val();
        $wrap.find('.fs-option, .fs-optgroup-label').removeClass('hidden');
        if ('' != keywords) {
            $wrap.find('.fs-option').each(function() {
                var regex = new RegExp(keywords, 'gi');
                if (null === $(this).find('.fs-option-label').text().match(regex)) {
                    $(this).addClass('hidden');
                }
            });
            $wrap.find('.fs-optgroup-label').each(function() {
                var num_visible = $(this).closest('.fs-optgroup').find('.fs-option:not(.hidden)').length;
                if (num_visible < 1) {
                    $(this).addClass('hidden');
                }
            });
        }
        $(this).focus();
        $wrap.find(".fs-check-all").prop("checked",$wrap.find('.fs-option:not(.hidden)').length == $wrap.find('.fs-option:not(.hidden).selected').length ? true : false);
        setIndexes($wrap);
    });
    
    $(document).on('click', '.fs-check .fs-check-all', function() {
        var $wrap = $(this).closest('.fs-wrap');
    	var checked = $(this).prop("checked");
    	if(checked){
	    	$wrap.find('.fs-option:not(.hidden)').each(function(i, el) {
	            $(el).addClass('selected');
	            window.fSelect.selectedList.push($(el).attr('data-value'));
			});
    	}else{
	    	$wrap.find('.fs-option:not(.hidden)').each(function(i, el) {
	            $(el).removeClass('selected');
	            window.fSelect.selectedList = [];
			});
    	}
        setValues(this, window.fSelect.selectedList);
        setText(this);
    });
    
    $(document).on('click', '.fs-check .fs-check-invert', function() {
        var $wrap = $(this).closest('.fs-wrap');
		window.fSelect.selectedList = [];
		$wrap.find('.fs-option:not(.hidden)').each(function(i, el) {
			if($(el).hasClass('selected')){
				$(el).removeClass('selected');
			}else{
				$(el).addClass('selected');
				window.fSelect.selectedList.push($(el).attr('data-value'));
			}
		});
		$wrap.find(".fs-check-all").prop("checked",$wrap.find('.fs-option:not(.hidden)').length == $wrap.find('.fs-option:not(.hidden).selected').length ? true : false);
        setValues(this, window.fSelect.selectedList);
        setText(this);
    });

    $(document).on('click', function(e) {
        var $el = $(e.target);
        var $wrap = $el.closest('.fs-wrap');
        if (0 < $wrap.length) {
            if ($el.hasClass('fs-label') || $el.hasClass('fs-arrow')) {
                window.fSelect.active = $wrap;
                var is_hidden = $wrap.find('.fs-dropdown').hasClass('hidden');
                $('.fs-dropdown').addClass('hidden');
                if (is_hidden) {
                    $wrap.find('.fs-dropdown').removeClass('hidden');
                }else {
                    $wrap.find('.fs-dropdown').addClass('hidden');
                }
                setIndexes($wrap);
            }
        }else {
            $('.fs-dropdown').addClass('hidden');
            window.fSelect.active = null;
        }
    });

    $(document).on('keydown', function(e) {
        var $wrap = window.fSelect.active;
        if (null === $wrap) {
            return;
        }else if (38 == e.which) { 
            e.preventDefault();
            $wrap.find('.fs-option').removeClass('hl');
            if (window.fSelect.idx > 0) {
                window.fSelect.idx--;
                $wrap.find('.fs-option[data-index=' + window.fSelect.idx + ']').addClass('hl');
                setScroll($wrap);
            }else {
                window.fSelect.idx = -1;
                $wrap.find('.fs-check input').focus();
            }
        }else if (40 == e.which) { 
            e.preventDefault();
            var last_index = $wrap.find('.fs-option:last').attr('data-index');
            if (window.fSelect.idx < parseInt(last_index)) {
                window.fSelect.idx++;
                $wrap.find('.fs-option').removeClass('hl');
                $wrap.find('.fs-option[data-index=' + window.fSelect.idx + ']').addClass('hl');
                setScroll($wrap);
            }
        }else if (32 == e.which || 13 == e.which) { 
            $wrap.find('.fs-option.hl').click();
        }else if (27 == e.which) { 
            $('.fs-dropdown').addClass('hidden');
            window.fSelect.active = null;
        }
    });
	
})(jQuery);
