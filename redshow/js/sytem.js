$(function() {
	//页面跳转
	$('#user').click(function() {
		$('.main-right').load("user.html");
	});
	//我的主页中页面跳转
	$('#myBooksjia').click(function(){
		$('.myMain-right').load("myBooks.html");
	});
	//我的主页中页面的跳转结束
	//角色页面跳转
	var currentPage = 1;
	var displayCount = 10;
	var totalPage = $('.sumPage').text();
	$('#role').click(function() {
		currentPage = 1;
		searchRole();
		$('.main-right').load("role.html");
	});
	//点击首页
	$('#first').click(function() {
		currentPage = 1;
		searchRole();
	});
	//点击上一页
	$('#back').click(function() {
		if(currentPage == 1) {
			return;
		} else {
			currentPage -= 1;
		}
		searchRole();
	});
	//点击下一页
	$('#next').click(function() {
		if(currentPage == totalPage) {
			return;
		} else {
			currentPage += 1;
		}
		searchRole();
	});
	//点击尾页
	$('#last').click(function() {
		currentPage = totalPage;
		searchRole();
	});
	//跳转指定页面
	$('#zhuan').click(function() {
		alert(totalPage);
		var pageText = $('#pageText').val();
		if(pageText == null || pageText == "") {
			currentPage = 1;
		} else if(pageText > totalPage || pageText == 0) {
			alert("请输入正确的页数");
		} else if(pageText <= totalPage && pageText > 0) {
			currentPage = pageText;
		}
		searchRole();
	});

	function searchRole() {
		$.ajax({
			type: "post",
			url: "http://127.0.0.1:8080/RedShowBooks/roleSearch",
			data: {
				currentPage: currentPage,
				displayCount: 6
			},
			success: function(resp) {
				var o = eval("(" + resp + ")");
				$('#databox').empty();
				var roles = o.roleMap;
				//这是其中一条数据roles={"roleMap":{"totalPage":3,"roles":[{"id":2,"remake":"管理系统","rolename":"管理员"}
				var roleses = roles.roles;
				for(var i in roleses) {
					var btnedit = $(' <td><button onclick="xiugai(' + roleses[i].id + ') " class="btn btn-primary" data-toggle="modal" data-target="#editModal">修改角色</button></td> ');
					var btndel = $(' <td><button onclick="shanchu(' + roleses[i].id + ') " class="btn btn-primary">删除角色</button></td> ');
					var td = $("<td>" + roleses[i].id + "</td>");
					var tr = $("<tr></tr>");
					tr.append(td);
					var td = $("<td>" + roleses[i].rolename + "</td>");
					tr.append(td);
					var td = $("<td>" + roleses[i].remake + "</td>");
					tr.append(td);
					tr.append(btnedit);
					tr.append(btndel);
					$('#databox').append(tr);
				}
				totalPage = roles.totalPage;
				$('.sumPage').html(totalPage);
				$('.nowPage').html(currentPage);
			}
		});
	}
	//角色页面跳转结束
	
	$('#menu').click(function() {
		$('.main-right').load("menu.html");
	});
	$('#log').click(function() {
		$('.main-right').load("log.html");
	});
	$('#notice').click(function() {
		$('.main-right').load("notice.html");
	});
	$('#books').click(function() {
		$('.main-right').load("books.html");
	});
	$('#booksType').click(function() {
		$('.main-right').load("booksType.html");
	});
	//页面跳转结束

	//删除提示
	$('#deluser').click(function() {
		var que = confirm("确实要删除数据吗？");
		if(que) {
			alert("删除成功");
		} else {
			alert("删除失败");
		}
	});
	$('#delrole').click(function() {
		var que = confirm("确实要删除数据吗？");
		if(que) {
			alert("删除成功");
		} else {
			alert("删除失败");
		}
	});
	$('#delmenu').click(function() {
		var que = confirm("确实要删除数据吗？");
		if(que) {
			alert("删除成功");
		} else {
			alert("删除失败");
		}
	});

});
//删除按钮操作
function shanchu(roleId) {
	var rId = roleId;
	if(confirm("确实要删除数据吗？")){
		$.ajax({
			type: "post",
			url: "http://localhost:8080/RedShowBooks/delRole",
			data: {
				roleId : rId
			},
			success: function(resp) {
				if(resp == 1) {
					alert("删除成功");
				} else {
					alert("删除失败");
				}
				window.location.href = "main.html";
			}
		});
	}
	
}
//删除按钮操作结束

//main界面修改按钮操作
function xiugai(roleId){
	alert(roleId);
	$.ajax({
		type: "post",
		url: "http://localhost:8080/RedShowBooks/roleById",
		data: {
			roleId : roleId
		},
		success: function(resp) {
			var o = eval("(" + resp + ")");
			//{"roleById":{"roleById":[{"id":23,"remake":"系统管理员描述","rolename":"系统管理员20"}]}}
			var roleByIds = o.roleById;
			var roleId = roleByIds.roleById;
			for(var i in roleId){
				$('.rId').val(roleId[i].id);
				$('.rolename').val(roleId[i].rolename);
				$('.rolemark').val(roleId[i].remake);
			}
		}
	});
}
//角色界面修改操作
$(function(){
	$('#updateRole').click(function(){
		var rId = $('.rId').val();
		var rolename = $('.rolename').val();
		var remake = $('.rolemark').val();
		$.ajax({
			type:"post",
			url:"http://localhost:8080/RedShowBooks/editRole",
			data : {
				roleId : rId,
				rolename : rolename,
				remake : remake
			},
			success : function(resp){
				if(resp == 1){
					alert("修改成功");
					window.location.href = "main.html";
				}else{
					alert("修改失败");
				}
			}
		});
	});
});
