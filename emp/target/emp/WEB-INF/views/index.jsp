<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 引入标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet"
	href="${APP_PATH}/static/bootstrap/css/bootstrap.css">
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-2.0.0.min.js"></script>
	
<style type="text/css">
.table tbody tr td{
     overflow: hidden; 
     text-overflow:ellipsis;  
     white-space: nowrap; 
}
</style>
</head>
<body>
	<!--开始的相对路径，赵资源，以当前资源的路径为基准，经常容易处问题 
以/开始的相对路径，以服务器的路径为标准 
	http://localhost:3306/
-->
<!-- 员工修改的模态框-->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
							<p class="form-control-static" id="empName_uptate_static"></p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="447332241@qq.com">
									<span  class="help-block"></span>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="M" checked="checked">
								<!-- values指定提交值 --> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_update_input" value="F"> 女
								</label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">部门</label>
							<!-- 部门提交部门id即可 -->
							<div class="col-sm-4"> <!-- 变成4列 -->
								<select class="form-control" name="dId" id="dept_upd_select">

								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">职位</label>
							<!-- 职位提交部门id即可 -->
							<div class="col-sm-4"> <!-- 变成4列 -->
								<select class="form-control" name="posId" id="pos_upd_select">

								</select>
							</div>
						</div>
						
						<div class="form-group">
						    <label class="col-sm-2 control-label">描述</label>
						    <div class="col-sm-10">
						    	<textarea class="form-control" name="description" id="desc_update_input" rows="3"></textarea>
						    </div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_uptate_btn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 员工添加的模态框-->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="empName">
									<span  class="help-block"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="447332241@qq.com">
									<span  class="help-block"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="M" checked="checked">
								<!-- values指定提交值 --> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_add_input" value="F"> 女
								</label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">部门</label>
							<!-- 部门提交部门id即可 -->
							<div class="col-sm-4"> <!-- 变成4列 -->
								<select class="form-control" name="deptId" id="dept_add_select">

								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">职位</label>
							<!-- 职位提交部门id即可 -->
							<div class="col-sm-4"> <!-- 变成4列 -->
								<select class="form-control" name="posId" id="pos_add_select">

								</select>
							</div>
						</div>
						
						<div class="form-group">
						    <label class="col-sm-2 control-label">描述</label>
						    <div class="col-sm-10">
						    	<textarea class="form-control" name="description" rows="3"></textarea>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 员工导入的模态框-->
	<div class="modal fade" id="empUploadModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工导入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="upload_form" enctype="multipart/form-data">
						<div class="form-group">
							<div class="col-sm-10">
								<input id="news_scheme_upload_file" name="file" type="file" style="width:300px" data-options="required:true,prompt:'请选择导出生成的excel文件'">
							</div>
							<div class="col-sm-10">
								<span><br><br></span>
								<a id="download_btn">下载清单模板</a>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_upload_btn">导入</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="containner">
		<!-- 语言切换 -->
		<div class="row">
			<div class="col-md-2 col-md-offset-10">
				<a href="/index?locale=zh_CN"><spring:message code="chinese" /></a>
				<a href="/index?locale=en_US"><spring:message code="english" /></a>
			</div>
		</div>
		<!--标题-->
		<div class="row">
			<div class="col-md-12">
				<h1><spring:message code="platform.home.title"></spring:message></h1>
			</div>
		</div>
		<!--按钮-->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn"><spring:message code="platform.home.add"></spring:message></button>
				<button class="btn btn-danger" id="emp_delete_all"><spring:message code="platform.home.delete"></spring:message></button>
				<button class="btn btn-primary" id="emp_upload_modal_btn"><spring:message code="platform.home.import"></spring:message></button>
				<button class="btn btn-danger" id="emp_download_all"><spring:message code="platform.home.export"></spring:message></button>
			</div>
		</div>
		
		<!-- 筛选 -->
		<div class="col-md-11 col-md-offset-1">
			<form class="form-inline" role="form" id="from_search">
			  <div class="form-group">
			    <label for="name"><spring:message code="platform.home.name"></spring:message></label>
			    <input type="text" class="form-control" name="empName" placeholder="请输入姓名">
			  </div>
			  <div class="form-group">
			    <label for="name"><spring:message code="platform.home.gender"></spring:message></label>
			    <select class="form-control" name="gender" >
			      <option value="">--<spring:message code="platform.home.choose"></spring:message>--</option>
			      <option value="M">男</option>
			      <option value="F">女</option>
			    </select>
			  </div>
			  <div class="form-group">
			    <label for="name"><spring:message code="platform.home.department"></spring:message></label>
			    <select class="form-control" name="deptId" id="select_dept_search" >
			    </select>
			  </div>
			  <div class="form-group">
			    <label for="name"><spring:message code="platform.home.position"></spring:message></label>
			    <select class="form-control" name="posId" id="select_pos_search" >
			    </select>
			  </div>
			  <button class="btn btn-primary" id="emp_search_btn"><spring:message code="platform.home.search"></spring:message></button>
			</form>
		</div>
		<!--表格数据-->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" style='table-layout:fixed;' id="emps_table">
					<thead>
						<tr>
						<th>
							<input type="checkbox" id="check_all">
						</th> 
							<th><spring:message code="platform.home.empID"></spring:message></th>
							<th><spring:message code="platform.home.name"></spring:message></th>
							<th><spring:message code="platform.home.gender"></spring:message></th>
							<th><spring:message code="platform.home.mail"></spring:message></th>
							<th><spring:message code="platform.home.department"></spring:message></th>
							<th><spring:message code="platform.home.position"></spring:message></th>
							<th><spring:message code="platform.home.description"></spring:message></th>
							<th><spring:message code="platform.home.operate"></spring:message></th>
						</tr>
					</thead>
					<tbody>
						<!-- 表格体 -->
					</tbody>

				</table>
			</div>
		</div>
		<!--分页信息-->
		<div class="row">
			<!--分页文字信息-->
			<div class="col-md-6" id="page_info_area"></div>
			<!--分页条信息-->
			<div class="col-md-6" id="page_nav"></div>
		</div>
	</div>
	<script type="text/javascript" src="/asset/employee.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/bootstrap/js/bootstrap.js"></script>
</body>
</html>