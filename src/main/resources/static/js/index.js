$(function () {
    $(".font-style").click(function () {
        alert($(this).text());
    });
    $(".bootstrap-table").on("load-success.bs.table", function () {
        $('.bootstrap-table tr td.text-overborder').each(function () {
            $(this).attr("title", $(this).text());
            $(this).css("cursor", 'pointer');
        });
    }).on("click", ".btn", function () {
        var id = $(this).attr("data-id");
        window.location.href = "/user/toUpdate?id="+id;
    }).on("click","");
    $(".file_upload").on("click",function () {
        var result = common.ajaxFileUpload({
            url:"common/ajaxFileUpload",
            fileElementId:"uploadFile",
            data:{'status':0},
            success:function (data,status) {
                if(data.code == 200){
                    alert(data.data);
                }else if(data.message){
                    alert(data.message);
                }
            }
        });
    });
});

function serachUser() {
    var param = getData("search_User");
    //queryParams(param);
    $(".bootstrap-table").bootstrapTable('refresh', param);
}

function toRegister() {
    $.ajax({
        type: 'post',
        data: getData("#registerForm"),
        url: '/user/saveUser',
        success: function (data) {
            alert(data.message);
        }
    });
}

function addUser() {
    window.location.href = "/user/toRegister";
}

function updateUser() {
    $.ajax({
        type: 'post',
        data: getData("#registerForm"),
        url: '/user/updateUser',
        success: function (data) {
            alert(data.message);
            setTimeout(function () {
                window.location.href = "/";
            },3000)
        }
    });
}

function operation(value, row, index) {
    var ope = '';
    ope += '<input type="button" data-id = "' + row.uid + '" class="btn" value="修改" >'
    return ope;
}

function showMessage() {
    var addr = $("input[name=address2]").val();
    var adds = addr.split("，");
    alert(adds[0] | "");

}