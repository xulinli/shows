$(function() {
	/*用户管理*/
	let nowpage = 1;
	let perpage = 2;
	let page;
	console.log(page);
	//查询
	$("#search").on("click", function() {
		
		query(1, perpage);
	});
	//首页
	$("#first").click(function() {
		nowpage = 1;
		query(nowpage, perpage);
	});
	//上一页
	$("#prve").click(function() {
		if(nowpage == 1) return;
		nowpage -= 1;
		query(nowpage, perpage);
	});
	//下一页
	$("#next").click(function() {
		if(nowpage == page) return;
		nowpage += 1;
		query(nowpage, perpage);
	});
	//尾页
	$("#last").click(function() {
		nowpage = page;
		query(nowpage, perpage);
	});
	//	指定页数
	$("#gogogo	").click(function() {
		let toPage = $("#pageText").val();
		console.log(toPage);
		if(toPage <= page) {
			query(toPage, perpage);
			toPage = "";
		} else {
			return;
		}
	})

	function footer(nowpage, page) {
		$("#nowpages").text(nowpage);
		$("#allpage").text(page);
	}

	function query(nowpage1, perpage) {
		let name = $("#username").val();
		let minage = $("#ageFrom").val();
		let maxage = $("#ageTo").val();
		$.ajax({
			url: 'userManage.do',
			type: 'post',
			data: JSON.stringify({
				name: name,
				minage: minage,
				maxage: maxage,
				nowpage: nowpage1,
				perpage: perpage
			}),
			async: false,
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function(dates) {
				console.log(dates);
				//				var dates = eval("(" + resp + ")");
				var pageCount = dates.pages;
				page = pageCount;
				var date = dates.list;
				$("#dates").empty();
				for(var i in date) {
					var tr = $("<tr></tr>");
					var td = $("<td></td>");
					td.append(parseInt(i) + 1);
					tr.append(td);
					td = $("<td class='name'></td>");
					td.append(date[i].username);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].sex);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].age);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].email);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].phone);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].address);
					tr.append(td);
					td = $("<td></td>");
					td.append(date[i].rolename);
					tr.append(td);
					td = $("<td></td>");
					td.append("<button id='edituser' class='btn btn-primary edit' data-toggle='modal' data-target='#editModal'>修改用户</button><button id='deluser' class='btn btn-primary delet'>删除用户</button>");
					tr.append(td);
					$("#dates").append(tr);
				}
				footer(nowpage, page);
			}
		})
	}
	/*用户管理新增用户*/
	let hadRegister = true;
	// 验证用户
	$('.username').blur(function() {
		var user = $('.username').val();
		if(user == "") {
			$('.tishi').html("用户名不能为空");
			return;
		} else {
			$('.tishi').html("");
			$.ajax({
				url: "yanzhengName.do",
				type: "POST",
				data: {
					user: user
				},
				success: function(resp) {
					if(resp == -1) {
						hadRegister = false;
						$('.tishi').html("该用户名已被注册");
						return;
					} else {
						hadRegister = true;
						$('.tishi').html("");
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
		if(pwd == "") {
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
	}

	$('#zhuce_add').click(function() {
//		init();
		var user = $('.username').val();
		var pwd = $('.pwd').val();
		var newpwd = $('.newpwd').val();
		var sex = $('input:radio[name="sex"]:checked').val();
		var age = $('#age').val();
		var email = $('#email').val();
		var phone = $('#phone').val();
		var address = $('#address').val();
		var checkedbox = $('input:radio[name="role"]:checked').val();
		//获取复选框的值
//		var box = document.getElementsByName("role");
//		var checkedbox = [];
//		for(var i in box) {
//			if(box[i].checked) {
//				checkedbox.push(box[i].value);
//			}
//		}
		console.log(checkedbox);
		if(user != "") {
			if(pwd != "") {
				if(newpwd != "") {
					if(newpwd == pwd) {
						if(hadRegister) {
							$.ajax({
								type: "POST",
								url: "addUser.do",
								data: JSON.stringify({
									username: user,
									password: pwd,
									newpwd: newpwd,
									sex: sex,
									age: age,
									email: email,
									phone: phone,
									checked: checkedbox,
									address: address
								}),
								traditional: true,
								async: false,
								dataType: 'json',
								contentType: 'application/json; charset=UTF-8',
								success: function(resp) {
									if(resp == 1) {
										alert("注册成功");
										$(".close").trigger("click");
										init();
										query(1, perpage);
									}
									if(resp == -1) {
										alert("注册失败");
										init();
									}
								}
							});
						} else {
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

	function init() {
		$('.username').val("");
		$('.pwd').val("");
		$('.newpwd').val("");
		$('input:radio:first').attr('checked', 'true');;
		$('#age').val("");
		$('#email').val("");
		$('#phone').val("");
		$('#address').val("");
		$('input:checkbox').each(function() {
			$(this).attr('checked', false);
		});
	}

	/*删除用户*/
	$("#dates").on("click", ".delet", function() {
		var mess = $(this).closest("tr").find(".name").text();
		console.log(mess);
		if(confirm("是否删除" + mess + "用户")) {
			$.ajax({
				type: "post",
				url: "deleteUser.do",
				data: {
					username: mess,
				},
			});
			query(1, perpage);
		}
	})

	/*修改用户*/
	$("#dates").on("click", ".edit", function() {
		var mess = $(this).closest("tr").find(".name").text();
		$.ajax({
			type: "post",
			url: "getUserforEdit.do",
			data: {
				username: mess,
			},
			success: function(date) {
				let user = eval("(" + date + ")");
				$(".username1").val(user.username);
				$(".username1").attr("disabled", "disabled");
				if("男" == (user.sex)) {
					$("input[name='sex'][value=男]").attr("checked", true);
				} else {
					$("input[name='sex'][value=女]").attr("checked", true);
				}
				$(".age").val(user.age);
				$(".email").val(user.email);
				$(".phone").val(user.phone);
				$(".address").val(user.address);

				//为复选框
				if ("系统管理员" == user.rolename) {
					$("input[name='role'][value=1]").attr("checked", true);
				} else if("普通管理员" == user.rolename){
					$("input[name='role'][value=2]").attr("checked", true);
				}else if("商户" == user.rolename){
					$("input[name='role'][value=3]").attr("checked", true);
				}else if("已注册用户" == user.rolename){
					$("input[name='role'][value=4]").attr("checked", true);
				}else if("游客" == user.rolename){
					$("input[name='role'][value=5]").attr("checked", true);
				}
			}
		});

	})

	$("#change").click(function() {
		var user = $('.username1').val();
		var sex = $('input:radio[name="sex"]:checked').val();
		var age = $('.age').val();
		var email = $('.email').val();
		var phone = $('.phone').val();
		var address = $('.address').val();
		var role =$('input:radio[name="role"]:checked').val();

		//获取复选框的值
//		var box = document.getElementsByName("role");
//		var checkedbox = [];
//		for(var i in box) {
//			if(box[i].checked) {
//				checkedbox.push(box[i].value);
//			}
//		}
		$.ajax({
			type: "post",
			url: "editUser.do",
			data: JSON.stringify({
				username: user,
				sex: sex,
				age: age,
				email: email,
				phone: phone,
				address: address,
				role: role
			}),
			async: false,
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			traditional: true,
			success: function(resp) {
				if(resp == 1) {
					alert("更新成功");
				} else {
					alert("更新失败");
				}
			}
		});
	})
})