ajaxFileUpload = function (param) {

    var success = param.success,
        error = param.error,
        dataType = param.dataType || 'json';

    return $.ajaxFileUpload({
        url:param.url,
        type: "POST",
        data:param.data,//要传到后台的参数
        secureuri : false,//是否启用安全提交，默认为false
        fileElementId:param.fileElementId,//文件选择框的id属性
        dataType: dataType,//服务器返回的格式
        async : false,
        success: function(data, status){
            if(success){
                success(data, status);
            }else if(data.message){
                alert(data.message);
            }else {
                alert("上传成功");
            }
        },
        error: function (data, status, e){
            if(error){
                error(data,status);
            }else if(data.message){
                alert(data.message);
            }else {
                alert(e.message);
            }
        }
    });

};

//获取标签下所有input标签的value
function getData(dataDom, data, config) {
    data = data ? data : {};
    $(dataDom).find('input[type = hidden],input[type = text],input[type = password],input[type = radio]:checked,select,textarea').each(function () {
        setVal(data, $(this).attr('name'), $(this).val(), config);
    });
    $(dataDom).find('input[type=checkbox]:checked').each(function () {
        setVal(data, $(this).attr('name'), $(this).val(), config);
    });
    return data;
}

//向data中封装新的value
function setVal(data, key, value, config) {
    if (!key || value === undefined) {
        return;
    }
    if (typeof (value) === 'string') {
        var nReplace = config && config.nReplace ? config.nReplace : '';
        value = value.replace(/\n/g, nReplace).trim();
    }
    if (!data) {
        data = {};
    }
    if (data[key] && typeof (value) === 'string') {
        var separator = config && config.separator ? config.separator : ',';
        data[key] = data[key] + separator + value;
    } else {
        data[key] = value;
    }
    return data;
}

function isBlank(str) {
    if (str.length == 0) {
        return true;
    }
    if (str == null) {
        return true;
    }
    if (str.replace(/(^s*)|(s*$)/g, "").length == 0) {
        return true;
    }
    var re = new RegExp("^[ ]+$");
    return re.test(str);

}

function initTable(opts) {
    var initOpts = {
        table: "#dataTable",
        toggle: "table",
        pagination: "true",
        sidePagination: "server",
        pageSize: "10",
        method: "get",
        pageList: "[]",
        uniqueId: "id",
        contentType: "application/x-www-form-urlencoded",
        cache: false,
        searchParam: "#searchParam",
        queryParams: function (params) {
            var searchParam = $(opts.searchParam).serializeArray(), data = $(".J_tab.active").data();
            $(searchParam).each(function () {
                params[this.name] = $.trim(this.value);
            });
            return $.extend(params, data);
        },
        onLoadError: function (status, data) {
            $(".both").remove();
        },
        onLoadSuccess: function (data) {

        }
    };
    var opts = $.extend(initOpts, opts);
    window.queue = function () {
        $(opts.table).bootstrapTable("refresh", {url: $(opts.table).data("url")});
    };
    return $(opts.table).bootstrapTable(opts);
}
