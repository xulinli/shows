$(function() {
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

	// 验证用户
	$('.username').blur(function() {
		var user = $('.username').val();
		if(user == "") {
			$('.tishi').html("用户名不能为空");
			return;
		} else {
			$('.tishi').html("");
			return;
		}
	});

	// 验证密码
	$('.pwd').blur(function() {
		var pwd = $('.pwd').val();
		if(pwd == "") {
			$('.tishimm').html("密码不能为空");
			return;
		} else {
			$('.tishimm').html("");
			return;
		}
	});

	// 验证新密码
	$('.newpwd').blur(function() {
		var pwd = $('.pwd').val();
		var newpwd = $('.newpwd').val();
		if(newpwd == "") {
			$('.tishinewmm').html("确认密码不能为空");
			return;
		} else if(pwd != newpwd) {
			$('.tishinewmm').html("两次输入密码不一致");
			return;
		} else {
			$('.tishinewmm').html("");
			return;
		}
	});

	$('#zhuce').click(function() {
		var user = $('.username').val();
		var pwd = $('.pwd').val();
		var newpwd = $('.newpwd').val();
		var sex = $('input:radio[name="sex"]:checked').val();
		var age = $('#age').val();
		var email = $('#email').val();
		var phone = $('#phone').val();
		var address = $('#address').val();
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/RedShowBooks/register",
			data: {
				username: user,
				password: pwd,
				newpwd: newpwd,
				sex: sex,
				age: age,
				email: email,
				phone: phone,
				address: address
			},
			success: function(resp) {
				if(resp == -2) {
					alert("用户不能为空");
				} else if(resp == -3) {
					alert("密码不能为空");
				} else if(resp == -4) {
					alert("确认密码不能为空");
				} else if(resp == -5) {
					alert("密码和确认密码不一致");
				} else if(resp == -1) {
					alert("该张号已经注册请重新输入");
				} else if(resp == 1) {
					alert("注册成功");
					location.href = "denglu.jsp"
				}
			}
		});
	});

	$('#denglu').click(function() {
		alert("sdf");
		var user = $('#username').val();
		var pwd = $('#pwd').val();
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/RedShowBooks/login",
			data: {
				username: user,
				password: pwd,
			},
			success: function(resp) {
				if(resp == -2) {
					alert("用户不能为空");
				} else if(resp == -3) {
					alert("密码不能为空");
				} else if(resp == 1) {
					location.href = "hopeshow.html"
				}
			}
		});
	});
});

// 省市县三级联动的js
$(function() {
	$('select#address').change(function() {
		var city = $('#address').val();
		$('#address2').empty();
		$.ajax({
			type: "POST",
			url: "area",
			data: {
				cityName: city,
				test: 1
			},
			success: function(resp) {
				var o = eval("(" + resp + ")");
				$('#address1').empty();
				var areas = o.areas;
				for(var i in areas) {
					var option = $('<option value="' +
						areas[i].cityName + '"></option>');
					option.text(areas[i].cityName);
					$('#address1').append(option);
				}
			}
		});
	});
	$('#address1').change(function() {
		var city = $('#address1').val();
		$.ajax({
			type: "POST",
			url: "area",
			data: {
				cityName: city,
				test: 2
			},
			success: function(resp) {
				var o = eval("(" + resp + ")");
				$('#address2').empty();
				var areas = o.areas;
				for(var i in areas) {
					var option = $('<option value="' +
						areas[i].cityName + '"></option>');
					option.text(areas[i].cityName);
					$('#address2').append(option);
				}
			}
		});
	});

});
// 省市县三级联动的js完结
