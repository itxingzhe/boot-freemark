$(function () {
    $(".font-style").click(function () {
        alert($(this).text());
    });
    $(".bootstrap-table").on("load-success.bs.table", function () {
        $('.bootstrap-table tr td.text-overborder').each(function () {
            $(this).attr("title", $(this).text());
            $(this).css("cursor", 'pointer');
        });
    }).on("click", ".btn-update", function () {
        var id = $(this).attr("data-id");
        window.location.href = "/user/toUpdate?id="+id;
    }).on("click", ".btn-role-manager", function () {
        var id = $(this).attr("data-id");
        window.location.href = "/role/manager?id=" + id;
    });

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
            setTimeout(function () {
                window.location.href = "/";
            }, 3000)
        }
    });
}

function doLogin() {
    var data = getData("#loginForm");
    $.ajax({
        type: 'post',
        data: getData("#loginForm"),
        url: '/user/toLogin',
        success: function (data) {
            $("body").html(data);
        },
        error: function (data) {
            $("body").html(data);
        }
    });
}

function addUser() {
    window.location.href = "/user/toRegister";
}

function logout() {
    window.location.href = "/user/logout";
}

function toLogin() {
    window.location.href = "/user/toLogin";
}

function showUserImg() {
    window.location.href = "/common/upload";
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

function showMessage() {
    var addr = $("input[name=address2]").val();
    var adds = addr.split("，");
    alert(adds[0] | "");

}

function userManager() {
    window.location.href = "/user/init";
}