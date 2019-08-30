$(function(){
	let uname;
	$.ajax({
		url: 'islogin.do',
//		data: {
//			username: '0'
//		},
//		type: 'post', //HTTP请求类型
//		xhrFields: {
//			withCredentials: true
//		},
//		crossDomain: true,
		success: function(resps) {
			let data = eval("(" + resps + ")");
			let name=data.username;
			uname=name;
			let role=data.role;
			if(name!=-1){
				$("#usersname").text(name);
				$("#usersrole").text(data.rolename);
//				$("#logout").css("display","block");
			}
		},
	});
	/*注销*/
	$("#logout").click(function() {
		$.ajax({
			url: "logout.do",
			type: 'post', //HTTP请求类型
			data: {
				name: uname
			},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(data) {
				if (data == 1) {
					location.href="hopeshow.html";
				}
			},
		});
	})
})
