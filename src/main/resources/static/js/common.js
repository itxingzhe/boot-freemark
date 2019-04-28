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

/**
 * 走马灯、无缝滚动
 *
 * @author wangyibin
 * @date 2019/1/7 15:17
 * @param parentId 标签ID（默认值：marquee）
 * @param direction 滚动方向（默认值：向左） 左：left；右：right；上：upward
 * @param time 滚动一次所需时间，单位毫秒（默认值：50）
 * @return
 *
 */
function marquee(parentId, time, direction) {
    parentId = parentId || "marquee";
    direction = direction || "left";
    var marq = document.getElementById(parentId);
    var text = marq.innerText;
    var chil;
    if (!!text) {
        chil = document.createElement("div");
        chil.innerText = text;
        chil.style.width = marq.clientWidth + "px";
        var height = marq.offsetHeight + "px";
        marq.innerText = "";
        marq.style.height = height;
        marq.appendChild(chil);
    } else {
        chil = marq.firstElementChild;
    }
    if ("left" == direction || "right" == direction) {
        chil.style.position = "absolute";
    }
    chil.style.whiteSpace = "nowrap";
    marq.style.position = "relative";
    marq.style.overflow = "hidden";
    var copyHtml = chil.cloneNode(true);
    var htmlWidth = chil.clientWidth;
    if ("left" == direction || "right" == direction) {
        copyHtml.style.left = htmlWidth + "px";
    }
    marq.appendChild(copyHtml);
    var x = 0;
    var fun = function () {
        if ("upward" == direction) {
            if (marq.scrollTop >= chil.offsetHeight) {
                marq.scrollTop = 0;
            }
            else {
                marq.scrollTop = marq.scrollTop + 1;
            }
        } else {
            if ("left" == direction) {
                chil.style.left = x + 'px';
                copyHtml.style.left = (x + htmlWidth) + 'px';
            } else if ("right" == direction) {
                chil.style.right = x + 'px';
                copyHtml.style.right = (x + htmlWidth) + 'px';
            }
            x--;
            if ((x + htmlWidth) == 0) {
                x = 0;
            }
        }
    };
    var m = time || 50;
    var inte = setInterval(fun, m);

    marq.onmouseover = function (ev) {
        clearInterval(inte);
    };
    marq.onmouseout = function (ev) {
        inte = setInterval(fun, m);
    };
}

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
    if (!key || !value) {
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

function haowan(label) {
  var a_idx = 0;
  $(label).click(function (e) {
    var a = new Array("❤加油❤", "❤努力❤", "❤IT行者❤", "❤关注我❤", "❤CSDN微博❤", "❤点赞❤",
        "❤打赏❤", "❤come on❤", "❤推荐一下❤", "❤支持一下❤", "❤关注我❤", "❤IT行者❤");
    var $i = $("<span></span>").text(a[a_idx]);
    a_idx = (a_idx + 1) % a.length;
    var x = e.pageX,
        y = e.pageY;
    $i.css({
      "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
      "top": y - 20,
      "left": x,
      "position": "absolute",
      "font-weight": "bold",
      "color": "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random())
          + "," + ~~(255 * Math.random()) + ")"
    });
    $("body").append($i);
    $i.animate({
          "top": y - 180,
          "opacity": 0
        },
        1500,
        function () {
          $i.remove();
        });
  });
}


