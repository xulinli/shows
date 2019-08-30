$(function() {
	let uname = "";
	$.ajax({
		url: 'islogin.do',
		type: 'post', //HTTP请求类型
		success: function(resps) {
			let data = eval("(" + resps + ")");
			if (data != -1) {
				if(data.haedurl!=null){
					var url="headImg/"+data.haedurl;
					$("#user_head_img").attr('src', url);
				}
				$("#usersname").html(data.username);
				uname=data.username;
			}
		},
	});


	$('#userLogin').click(function() {
		var user = $('#id_account_l').val();
		var pwd = $('#id_password_l').val();
		if (user == "") {
			$("#id_account_l").focus();
			alert("用户不能为空");
			return;
		}
		if (pwd == "") {
			$("#id_account_l").focus();
			alert("密码不能为空");
			return;
		}
		$("#userLogin").html("登录中..."), $("#userLogin").attr("disabled", "disabled")
		$.ajax({
			type: "POST",
			url: "login.do",
			data: {
				username: user,
				password: pwd,
			},
			success: function(resps){
				let data = eval("(" + resps + ")");
				let resp = data.rersult;
				let role=data.user;
				if(role != null){
					if(role.rolename =="普通管理员" || role.rolename =="系统管理员" || role.rolename =="商户"){
						location.href="main.html";
					}
				}
				if (resp == -2) {
					alert("用户不能为空");
					$("#userLogin").html("登录"), $("#userLogin").removeAttr('disabled');
				} else if (resp == -3) {
					alert("密码不能为空");
					$("#userLogin").html("登录"), $("#userLogin").removeAttr('disabled');
				} else if (resp == 1) {
					$("#loginModal").modal("hide");
					if(data.haedurl!=null){
						var url1="headImg/"+data.haedurl;
						$("#user_head_img").attr('src', url1);
					}
					$("#usersname").html(role.username);
					$("#userLogin").html("登录"), $("#userLogin").removeAttr('disabled');
					uname = role.username;
				} else if (resp == -1) {
					$("#login-form-tips").html("账号或者密码错误，请重新输入").show(500);
					$("#userLogin").html("登录"), $("#userLogin").removeAttr('disabled');
				} else if (resp == 2) {
					$("#login-form-tips").html("该账号已经登陆，请勿重复登陆").show(500);
					$("#userLogin").html("登录"), $("#userLogin").removeAttr('disabled');
				}
			}
		});
	})

	/*注销*/
	$(".logout_1").click(function() {
		$.ajax({
			url: "logout.do",
			type: 'post', //HTTP请求类型
			data: {
				name: uname
			},
			success: function(data) {
				if (data == 1) {
					$("#user_head_img").attr('src', "headImg/user.png");
					$("#usersname").html("");
				}
			},
		});
	})

})
