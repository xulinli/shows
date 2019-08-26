$(function() {
	let sheninit = {
		parentid: 0
	};
	let shiinit = {
		parentid: 11
	};
	let xianinit = {
		parentid: 1101
	};

	function init(val1, val2, val3) {
		searchshen(val1, $("#shen"));
	};
	//省級
	init(sheninit, shiinit, xianinit);
	//市级
	$('#shen').click(function() {
		var v = $(this).find("option:selected").val();
		let shi = {
			parentid: v
		};
		searchshen(shi, $("#shi"));

	});
	//区县
	$('#shi').click(function() {
		var c = $("#shi").find("option:selected").val();
		let xian = {
			parentid: c
		};
		searchshen(xian, $("#xian"));
	});

	function searchshen(local, posi) {
		$.ajax({
			url: 'serchLocal.do',
			type: "POST",
			data: local,
			success: function(date) {
				let resp = eval("(" + date + ")");
				let b = posi;
				b.empty();
				let a;
				for (var i in resp) {
					a = $("<option value=" + resp[i].codeid + "></option>");
					a.text(resp[i].cityName);
					b.append(a);
				}
			}
		});
	}






	var index = 0;
	var timer = setInterval(function() {
		index = (index == 4) ? 0 : index + 1;
		$('.center-bj>div').hide().eq(index).show();
	}, 2000);
	$('.hover1>li').mouseover(function() {
		$(".center-bj>div").eq($(this).index()).show().siblings().hide();
	});

	$('#qbfl1').mouseover(function() {
		$('.qbfl').show();
	}).mouseout(function() {
		$('.qbfl').hide();
	});
	let hadRegister = true;
	// 验证用户
	$('.username').blur(function() {
		var user = $('.username').val();
		if (user == "") {
			$('.tishi').html("用户名不能为空");
			return;
		} else {
			$('.tishi').html("");
			$.ajax({
				url: "yanzhengName.do",
				type: "POST",
				data: {user:user},
				success: function(resp) {
					if (resp == -1) {
						hadRegister = false;
						$('.tishi').html("该用户名已被注册");
						return;
					}else{
						$('.tishi').html("");
						hadRegister = true;
						return;
					}
				}
			})
			return;
		}
	});

	// 验证密码
	$('.pwd').blur(function() {
		nameisempty();
	});

	function nameisempty() {
		var pwd = $('.pwd').val();
		if (pwd == "") {
			$('.tishimm').html("密码不能为空");
			return;
		} else {
			$('.tishimm').html("");
			return;
		}
	}
	// 验证新密码
	$('.newpwd').blur(function() {
		passyan();
	});

	function passyan() {
		var pwd = $('.pwd').val();
		var newpwd = $('.newpwd').val();
		if (newpwd == "") {
			$('.tishinewmm').html("确认密码不能为空");
			return;
		} else if (pwd != newpwd) {
			$('.tishinewmm').html("两次输入密码不一致");
			return;
		} else {
			$('.tishinewmm').html("");
			return;
		}
	}


	$('#zhuce').click(function() {
		var user = $('.username').val();
		var pwd = $('.pwd').val();
		var newpwd = $('.newpwd').val();
		var sex = $('input:radio[name="sex"]:checked').val();
		var age = $('#age').val();
		var email = $('#email').val();
		var phone = $('#phone').val();
		var address1 = $('#shen').val();
		var address2 = $('#shi').val();
		var address3 = $('#xian').val();
		if (user != "") {
			if (pwd != "") {
				if (newpwd != "") {
					if (newpwd == pwd) {
						if (hadRegister) {
							$.ajax({
								type: "POST",
								url: "register.do",
								data: JSON.stringify({
									username: user,
									password: pwd,
									newpwd: newpwd,
									sex: sex,
									age: age,
									email: email,
									phone: phone,
									addres1: address1,
									addres2: address2,
									addres3: address3
								}),
								async:false,
        						dataType:'json',
        						contentType: 'application/json; charset=UTF-8',
								success: function(resp) {
									if (resp == 1) {
										alert("注册成功");
										
										location.href = "http://localhost:1111"
//										location.href = "http://127.0.0.1:8020/redshow/hopeshow.html"
									}
									if (resp == -1) {
										alert("注册失败");
										location.reload();
									}
								}
							});
						}else{
							alert("您的账号已被注册")
						}
					} else {
						alert("密码和确认密码不一致");
					}
				} else {
					alert("确认密码不能为空");
				}
			} else {
				alert("密码不能为空");
			}
		} else {
			alert("用户不能为空");
		}
	});

	
})
