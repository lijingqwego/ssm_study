var totalRecord,currentPage;
var appPath;
//1.在页面加载完成以后，直接去发送一个ajax请求,要到分页数据
$(function() {
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	appPath=prePath+postPath;
	//alert(appPath);
	//去首页
	getDepts($("#select_dept_search"),"search");
	getPositions($("#select_pos_search"),"search");
	to_page(1);
});

function to_page(curPage) {
	$.ajax({
		url : appPath+"/emp/list/"+curPage,
		data:$("#from_search").serialize(),
		async:false,
		type : "POST",
		success : function(result) {
			//console.log(result);
			//return ;
			//1.解析并显示员工数据
			build_emps_table(result);
			//2.解析并显示分页信息
			build_page_info(result);
			//解析显示分页条数据
			build_page_nav(result);

		}
	});
}

function build_emps_table(result) {
	//清空table 表格
	//emps_table tbody
	$("#emps_table tbody").empty();
	var emps = result.extend.pageInfo.list;
	$.each(emps, function(index, item) {
		var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
		var empIdTd = $("<td></td>").append(item.empId);
		var empNameTd = $("<td></td>").append(item.empName);
		var genderTd = $("<td></td>").append(
				item.gender == 'M' ? "男" : "女");
		var emailTd = $("<td></td>").append(item.email);
		var deptName = $("<td></td>").append(item.department.deptName);
		var posName = $("<td></td>").append(item.position.posName);
		var description = $("<td></td>").append(item.description);
		var editBtn = $("<button></button>").addClass(
				"btn btn-primary btn-sm edit_btn").append(
				$("<span></span>").addClass(
						"glyphicon glyph icon-pencil")).append("编辑");
		
		//为编辑按钮添加一个自定义的属性,来表示当前员工的id
		editBtn.attr("edit-id",item.empId);
	 
		var delBtn = $("<button></button>").addClass(
				"btn btn-danger btn-sm delete_btn").append(
				$("<span></span>")
						.addClass("glyphicon glyphicon-trash")).append(
				"删除");
		
		//为删除按钮添加一个自定义的属性来表示当前员工的删除id
		delBtn.attr("del-id",item.empId);
		var btnTd = $("<td></td>").append(editBtn).append(" ").append(
				delBtn);
		//append方法执行完成以后还是返回原来的元素
		$("<tr></tr>").append(checkBoxTd)
					.append(empIdTd)
					.append(empIdTd)
					.append(empNameTd)
			     	.append(genderTd)
			    	.append(emailTd)
			    	.append(deptName)
			    	.append(posName)
			    	.append(description)
				    .append(btnTd)
				    .appendTo("#emps_table tbody")
	})
}

//解析显示分页信息
function build_page_info(result) {
	//page_info_area
	$("#page_info_area ").empty();
	$("#page_info_area").append(
			"当前第" + result.extend.pageInfo.pageNum + "页,总"
					+ result.extend.pageInfo.pages + "页,总"
					+ result.extend.pageInfo.total + "条记录数");
 totalRecord=result.extend.pageInfo.total; //赋值为总记录数
 currentPage = result.extend.pageInfo.pageNum;

}
//解析显示分页条
function build_page_nav(result) {
	//page_nav
	$("#page_nav ").empty();
	var ul = $("<ul></ul>").addClass("pagination");
	//构建元素
	var firstPageLi = $("<li></li>").append(
			$("<a></a>").append("首页").attr("href", "#"));
	var prePageLi = $("<li></li>").append(
			$("<a></a>").append("&laquo;"));
	if (result.extend.pageInfo.hasPreviousPage == false) {
		firstPageLi.addClass("disabled");
		prePageLi.addClass("disabled");
	}
	//为元素添加点击翻页的事件
	firstPageLi.click(function() {
		to_page(1);
	})

	prePageLi.click(function() {
		to_page(result.extend.pageInfo.pageNum - 1);
	})

	var nextPageLi = $("<li></li>").append(
			$("<a></a>").append("&raquo;"));

	var lastPageLi = $("<li></li>").append(
			$("<a></a>").append("末页").attr("href", "#"));

	if (result.extend.pageInfo.hasNextPage == false) {
		nextPageLi.addClass("disabled");
		lastPageLi.addClass("disabled");
	} else {
		nextPageLi.click(function() {
			to_page(result.extend.pageInfo.pageNum + 1);
		})

		lastPageLi.click(function() {
			to_page(result.extend.pageInfo.pages);
		})
	}

	//添加首页和前一页的提示
	ul.append(firstPageLi).append(prePageLi);
	//1.2.3遍历给ul中添加页码提示
	$.each(result.extend.pageInfo.navigatepageNums, function(index,
			item) {
		var numLi = $("<li></li>").append($("<a></a>").append(item));
		if (result.extend.pageInfo.pageNum == item) {
			numLi.addClass("active");
		}
		numLi.click(function() {
			to_page(item);
		})
		ul.append(numLi);
	});
	//添加下一页和末页提示
	ul.append(nextPageLi).append(lastPageLi);

	//把ul加入到nav元素中
	var navEle = $("<nav></nav>").append(ul);
	navEle.appendTo("#page_nav");

}

//清空表单样式以及内容
function reset_form(ele){
	$(ele)[0].reset;
	//清空表单样式
	$(ele).find("*").removeClass("has-error has-success");
	$(ele).find(".help-block").text("");
}

//点击新增按钮弹出模态框
$("#emp_add_modal_btn").click(function() {
	//清除表单数据(表单完整重置(表单的数据，表单的样式))
	reset_form("#empAddModal form");
	$("#empAddModal form")[0].reset();
	//发送ajax请求，查出部门信息，显示在下拉列表中
	getDepts($("#dept_add_select"),"add");
	getPositions($("#pos_add_select"),"add");
	//弹出模态框
	$("#empAddModal").modal({ //利用js创建模态框
		backdrop : "static"
	});
});

//查出所有的部门信息并显示在下拉列表中
function getDepts(ele,tag){
	//清空之前下拉列表的值
	$(ele).empty();
	if(tag=='search'){
		$("<option ></option>").append("--请选择--").attr("value","").appendTo(ele);
	}
	$.ajax({
		url: appPath+"/dept/list",
		async:false,
		type:"GET",
		success:function(result){
//			console.log(result);
			//显示部门信息在下拉列表中
			//$("#dept_add_select").append("")
			$.each(result.extend.list,function(){
			var optionEle = $("<option ></option>").append(this.deptName).attr("value",this.deptId);
			optionEle.appendTo(ele);
			})
		}
	});		
};	

//查出所有的职位信息并显示在下拉列表中
function getPositions(ele,tag){
	//清空之前下拉列表的值
	$(ele).empty();
	if(tag=='search'){
		$("<option ></option>").append("--请选择--").attr("value","").appendTo(ele);
	}
	$.ajax({
		url: appPath+"/pos/list",
		async:false,
		type:"GET",
		success:function(result){
			console.log(result);
			//显示部门信息在下拉列表中
			//$("#dept_add_select").append("")
			$.each(result.extend.list,function(){
			var optionEle = $("<option ></option>").append(this.posName).attr("value",this.posId);
			optionEle.appendTo(ele);
			})
		}
	});		
};	


function validate_add_form(){
	//1.拿到要校验的数据.使用正则表达式
	var empName = $("#empName_add_input").val(); 
	var regName=  /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
	//小写或者大写的a-z或者0-9   或者_或者-  3到16位  或者中文2到5位
	if(!regName.test(empName)){
		//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
		show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合");
		return false;
	}else{
		show_validate_msg("#empName_add_input","success"," ");
	}
	
	//2.校验邮箱信息
	var email = $("#email_add_input").val(); //拿到email的值 
	var regemail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!regemail.test(email)){
	//	alert("邮箱格式不正确");
	//应该清空这个元素之前的样式
		show_validate_msg("#email_add_input","error","邮箱格式不正确");
		return false;
	}else{
		show_validate_msg("#email_add_input","success"," ");
		return true;
		
	}
};

function show_validate_msg(ele,status,msg){
	//清除当前元素的检验状态
	$(ele).parent().removeClass("has-success has-error");
	$(ele).next("span").text(" ");
	if("success"==status){
		$(ele).parent().addClass("has-success");
		$(ele).next("span").text(msg);
		
	}else if("error"==status){
		$(ele).parent().addClass("has-error");
		$(ele).next("span").text(msg);
	}
}

//校验用户名是否可用
$("#empName_add_input").change(function(){
	//发送ajax请求校验用户名是否可用
	var empName = this.value;
	$.ajax({
		url:appPath+"/emp/checkName",
		data: "empName="+empName,
		type:"POST",
		success:function(result){
//			console.log(result);
			if(result.code==200 && result.extend.isExist==false){
				 show_validate_msg("#empName_add_input","success","用户名可用");
				 $("#emp_save_btn").attr("ajax_va","success"); //给按钮添加一个属性 内容为success
				
			}else{
				show_validate_msg("#empName_add_input","error",result.extend.va_msg);
				 $("#emp_save_btn").attr("ajax_va","error");
			}
		}
		 
	});
});

//点击保存，保存员工.
$("#emp_save_btn").click(function(){
	
	//1.模态框中填写的表单数据提交给服务器进行保存
	//2.现对要提交给服务器的数据进行校验
	  if(!validate_add_form()){
		return false;
	}  
	//3.判断之前的ajax用户名校验是否成功，如果成功
	if($(this).attr("ajax_va")=="error"){
		return false;
	}
	//4.发送ajax请求保存员工 
	alert($("#empAddModal form").serialize());
 	$.ajax({
		url:appPath+"/emp/save",
		type:"POST",
		data:$("#empAddModal form").serialize(),
		success:function(result){
//				console.log(result);
				alert(result.msg);
				if(result.code == 200){
					//员工保存成功
					//1.关闭模态框
					$("#empAddModal").modal("hide");
					//2.来到最后一页,显示刚才保存的数据
					//发送ajax请求显示最后一页数据
					//
					to_page(1); //数字足够大会直接去最后一面
				}else{
					//显示失败信息
					//console.log(result);
					//有哪个字段的错误信息就显示哪个字段
					if( undefined != result.extend.errorFields.email){
						//显示邮箱错误信息
						show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
					}
					if(undefined != result.extend.errorFields.empName){
						//显示员工名字的错误信息
						show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
					}
					
				}
		}
	}) 
});

 
//1.我们是按钮创建之前就绑定了click,所以绑定不上
//1.我们可以在创建按钮的时候绑定事件.  2.绑定点击 live
//jquery新版没有live,使用on进行替代
//
$(document). on("click", ".edit_btn" ,function(){
	//0.查出员工信息，显示员工信息
	//1.查出部门信息，并显示部门列表
	getDepts($("#dept_upd_select"),"upd");
	getPositions($("#pos_upd_select"),"upd");
 	getEmp($(this).attr("edit-id")); //获取当前按钮的id 
 	//3.把员工的id传递给模态框的更新按钮
 	$("#emp_uptate_btn").attr("edit-id",$(this).attr("edit-id"));
	$("#empUpdateModal").modal({ //利用js创建模态框
		backdrop : "static"
	});
							
});

function getEmp(id){
	$.ajax({
		url:appPath+"/emp/info/"+id,
		type:"GET",
		success:function(result){
//			console.log(result);
			var empData = result.extend.emp;
			$("#empName_uptate_static").text(empData.empName);
			$("#email_update_input").val(empData.email);
			$("#empUpdateModal input[name=gender]").val([empData.gender]); //当单选框被选中
			$("#dept_upd_select").val([empData.deptId]);
			$("#pos_upd_select").val([empData.posId]);
			$("#desc_update_input").text(empData.description);
		} 
		
	})
}

//点击更新,更新员工信息
$("#emp_uptate_btn").click(function(){
	//验证邮箱是否合法
	//1.校验邮箱信息
	var email = $("#email_update_input").val(); //拿到email的值 
	var regemail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!regemail.test(email)){
	//应该清空这个元素之前的样式
		show_validate_msg("#email_update_input","error","邮箱格式不正确"); 
		return false;
	}else{
		show_validate_msg("#email_update_input","success"," ");	
	}
	//2.发送ajax请求保存更新员工的数据
	$.ajax({
		url:appPath+"/emp/update/"+$(this).attr("edit-id"),
		type:"PUT",
		data:$("#empUpdateModal form").serialize(), //获取表单序列化后的结果,改方法为put
		success:function(result){
			alert(result.msg);
			//1.关闭对话框
			$("#empUpdateModal").modal("hide");
			//2.回到本页面
			to_page(currentPage);
		}
	});			
});

//单个删除
$(document). on("click", ".delete_btn" ,function(){
	 //1.弹出是否确认删除对话框
	 var ids=[];
	 var empName = $(this).parents("tr").find("td:eq(2)").text();
	 var empId = $(this).attr("del-id");
	 ids.push(empId);
	 //alert($(this).parents("tr").find("td:eq(1)").text());
	 if(confirm("确定删除["+empName+"]吗?")){
		 //确认.发送ajax请求删除
		 $.ajax({
			 url:appPath+"/emp/dels",
			 contentType : "application/json",
			 data:JSON.stringify(ids),
			 type:"DELETE",
			 success:function(result){
				 alert(result.msg);
				 //回到本页
				 to_page(currentPage);
			 }
		 })
	 }
})

//完成全选/全不选
$("#check_all").click(function(){
	//attr获取checked是undefined 
	//我们这些dom原生的属性:attr获取自定义属性的值,prop获取原生的值
	//alert($(this).prop("checked"));
	$(".check_item").prop("checked",$(this).prop("checked"));
});

//check_item
$(document). on("click", ".check_item" ,function(){
	//判断当前选中的元素是不是5个
	var flag = $(".check_item:checked").length==$(".check_item").length;
	$("#check_all").prop("checked",flag);
	
});

//点击全部删除,就批量删除
$("#emp_delete_all").click(function(){
	//$(".check_item:checked")
	var names=[];
	var ids=[];
	$.each($(".check_item:checked"),function(){
		//this
		names.push($(this).parents("tr").find("td:eq(2)").text());
		//组装员工id字符串
		ids.push($(this).parents("tr").find("td:eq(1)").text());
		
	});
	//alert(names);
	//alert(ids);
	if(confirm("确认删除【"+names+"】吗")){
		//发送ajax请求删除
		$.ajax({
			url:appPath+"/emp/dels",
			contentType : "application/json",
			data:JSON.stringify(ids),
			type:"DELETE",
			success:function(result){
				alert(result.msg);
				//回到当前页面
				to_page(currentPage);
			}
		});
	}
});


//弹出导入员工列表模态框
$('#emp_upload_modal_btn').click(function() {
	//清除表单数据(表单完整重置(表单的数据，表单的样式))
	reset_form("#empUploadModal form");
	$("#empUploadModal form")[0].reset();
	$("#empUploadModal").modal({ //利用js创建模态框
		backdrop : "static"
	});
});

//下载清单文件
$('#download_btn').click(function() {
	window.location.href=appPath+'/emp/download/1';
});

//导出员工列表
$('#emp_download_all').click(function() {
	window.location.href=appPath+'/emp/download/2';
});

$('#emp_upload_btn').on('click',function(){
    	//使用FormData，进行Ajax请求并'上传文件'.普通异步传递无法传传递文件,下面一句相当于把表单中的所有属性都封装进去了.
        var formData = new FormData(document.getElementById("upload_form")); 
        $.ajax({ 
            url : appPath+'/emp/upload', 
            type : 'POST', 
            data : formData, 
            // 告诉jQuery不要去处理发送的数据
            processData : false, 
            // 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            success : function(data) {
            	if(data.code==200){
            		$('#empUploadModal').modal('hide');
            		to_page(1);
        		}
            	console.log(data);
            }
        });
});

//筛选员工列表
$("#emp_search_btn").click(function(){
	to_page(1);
});
