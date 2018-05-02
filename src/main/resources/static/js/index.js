$(function () {
    $(".font-style").click(function () {
        alert($(this).text());
    });
})

function toRegister() {
    $.ajax({
        type: 'post',
        data: getData("#registerForm"),
        url:'/user/saveUser',
        success:function (data) {
            alert(data.message)
        }
    });
}function updateUser() {
    $.ajax({
        type: 'post',
        data: getData("#registerForm"),
        url:'/user/updateUser',
        success:function (data) {
            alert(data.message)
        }
    });
}

function getData(dataDom, data, config) {
    data = data ? data : {};
    $(dataDom).find('input[type = hidden],input[type = text],input[type = redio]:checked,select,textarea').each(function () {
        setVal(data,$(this).attr('name'),$(this).val(),config);
    });
    $(dataDom).find('input[type=checkbox]:checked').each(function () {
        setVal(data,$(this).attr('name'),$(this).val(),config);
    })
    return data;
}

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
