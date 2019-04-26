$(function () {
  $(".font-style").click(function () {
    alert($(this).text());
  });

  $(".file_upload").on("click", function () {
    var result = ajaxFileUpload({
      url: "/file/uploadExcel",
      fileElementId: "uploadFile",
      data: {'status': 0},
      success: function (data, status) {
        if (data.code == 200) {
          alert(data.data);
        } else if (data.message) {
          alert(data.message);
        }
      }
    });
  });
});

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
  var t = new Date().getTime();
  $.ajax({
    type: 'post',
    data: getData("#loginForm"),
    url: '/user/doLogin?t=' + t,
    success: function (data) {
      if (!data.code) {
        $("body").html(data);
      } else if (data.code == 200) {
        //window.open(data.data,'_self');
        window.location.href=data.data;
      } else {
        $("#message").remove();
        $("body").append("<p id='message'>" + data.message + "</p>");
      }
    },
    error: function (data) {
      if (!data.code) {
        $("body").html(data)
      } else {
        $("#message").remove();
        $("body").append(data.message);
      }
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

function showMessage() {
  var addr = $("input[name=address2]").val();
  var adds = addr.split("，");
  alert(adds[0] | "");

}

function userManager() {
  window.location.href = "/user/init";
}