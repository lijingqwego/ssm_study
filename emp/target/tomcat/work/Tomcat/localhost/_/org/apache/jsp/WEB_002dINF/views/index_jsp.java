/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-07-22 14:05:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 引入标签库 -->\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>员工列表</title>\r\n");

	pageContext.setAttribute("APP_PATH", request.getContextPath());

      out.write("\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/bootstrap/css/bootstrap.css\">\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/js/jquery-2.0.0.min.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".table tbody tr td{\r\n");
      out.write("     overflow: hidden; \r\n");
      out.write("     text-overflow:ellipsis;  \r\n");
      out.write("     white-space: nowrap; \r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!--开始的相对路径，赵资源，以当前资源的路径为基准，经常容易处问题 \r\n");
      out.write("以/开始的相对路径，以服务器的路径为标准 \r\n");
      out.write("\thttp://localhost:3306/\r\n");
      out.write("-->\r\n");
      out.write("<!-- 员工修改的模态框-->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"empUpdateModal\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\t\taria-labelledby=\"myModalLabel\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\" >员工修改</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<form class=\"form-horizontal\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">姓名</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"form-control-static\" id=\"empName_uptate_static\"></p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">邮箱</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" name=\"email\" class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tid=\"email_update_input\" placeholder=\"447332241@qq.com\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  class=\"help-block\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">性别</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"radio-inline\"> <input type=\"radio\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"gender\" id=\"gender1_update_input\" value=\"M\" checked=\"checked\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- values指定提交值 --> 男\r\n");
      out.write("\t\t\t\t\t\t\t\t</label> <label class=\"radio-inline\"> <input type=\"radio\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"gender\" id=\"gender2_update_input\" value=\"F\"> 女\r\n");
      out.write("\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">部门</label>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- 部门提交部门id即可 -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-4\"> <!-- 变成4列 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"form-control\" name=\"dId\" id=\"dept_upd_select\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">职位</label>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- 职位提交部门id即可 -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-4\"> <!-- 变成4列 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"form-control\" name=\"posId\" id=\"pos_upd_select\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t    <label class=\"col-sm-2 control-label\">描述</label>\r\n");
      out.write("\t\t\t\t\t\t    <div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t    \t<textarea class=\"form-control\" name=\"description\" id=\"desc_update_input\" rows=\"3\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" id=\"emp_uptate_btn\">更新</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 员工添加的模态框-->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"empAddModal\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\t\taria-labelledby=\"myModalLabel\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\" id=\"myModalLabel\">员工添加</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<form class=\"form-horizontal\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">姓名</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" name=\"empName\" class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tid=\"empName_add_input\" placeholder=\"empName\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  class=\"help-block\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">邮箱</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" name=\"email\" class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tid=\"email_add_input\" placeholder=\"447332241@qq.com\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  class=\"help-block\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">性别</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"radio-inline\"> <input type=\"radio\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"gender\" id=\"gender1_add_input\" value=\"M\" checked=\"checked\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- values指定提交值 --> 男\r\n");
      out.write("\t\t\t\t\t\t\t\t</label> <label class=\"radio-inline\"> <input type=\"radio\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"gender\" id=\"gender2_add_input\" value=\"F\"> 女\r\n");
      out.write("\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">部门</label>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- 部门提交部门id即可 -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-4\"> <!-- 变成4列 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"form-control\" name=\"deptId\" id=\"dept_add_select\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">职位</label>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- 职位提交部门id即可 -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-4\"> <!-- 变成4列 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"form-control\" name=\"posId\" id=\"pos_add_select\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t    <label class=\"col-sm-2 control-label\">描述</label>\r\n");
      out.write("\t\t\t\t\t\t    <div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t    \t<textarea class=\"form-control\" name=\"description\" rows=\"3\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" id=\"emp_save_btn\">保存</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 员工导入的模态框-->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"empUploadModal\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\t\taria-labelledby=\"myModalLabel\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\" id=\"myModalLabel\">员工导入</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<form class=\"form-horizontal\" id=\"upload_form\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"news_scheme_upload_file\" name=\"file\" type=\"file\" style=\"width:300px\" data-options=\"required:true,prompt:'请选择导出生成的excel文件'\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<span><br><br></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a id=\"download_btn\">下载清单模板</a>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" id=\"emp_upload_btn\">导入</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"containner\">\r\n");
      out.write("\t\t<!--标题-->\r\n");
      out.write("\t\t<div class=\"row\"></div>\r\n");
      out.write("\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t<h1>员工管理系统</h1>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--按钮-->\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-md-4 col-md-offset-8\">\r\n");
      out.write("\t\t\t\t<button class=\"btn btn-primary\" id=\"emp_add_modal_btn\">新增</button>\r\n");
      out.write("\t\t\t\t<button class=\"btn btn-danger\" id=\"emp_delete_all\">删除</button>\r\n");
      out.write("\t\t\t\t<button class=\"btn btn-primary\" id=\"emp_upload_modal_btn\">导入</button>\r\n");
      out.write("\t\t\t\t<button class=\"btn btn-danger\" id=\"emp_download_all\">导出</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 筛选 -->\r\n");
      out.write("\t\t<div class=\"col-md-11 col-md-offset-1\">\r\n");
      out.write("\t\t\t<form class=\"form-inline\" role=\"form\" id=\"from_search\">\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <label for=\"name\">姓名</label>\r\n");
      out.write("\t\t\t    <input type=\"text\" class=\"form-control\" name=\"empName\" placeholder=\"请输入姓名\">\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <label for=\"name\">性别</label>\r\n");
      out.write("\t\t\t    <select class=\"form-control\" name=\"gender\" >\r\n");
      out.write("\t\t\t      <option value=\"\">--请选择--</option>\r\n");
      out.write("\t\t\t      <option value=\"M\">男</option>\r\n");
      out.write("\t\t\t      <option value=\"F\">女</option>\r\n");
      out.write("\t\t\t    </select>\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <label for=\"name\">部门</label>\r\n");
      out.write("\t\t\t    <select class=\"form-control\" name=\"deptId\" id=\"select_dept_search\" >\r\n");
      out.write("\t\t\t    </select>\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <label for=\"name\">职位</label>\r\n");
      out.write("\t\t\t    <select class=\"form-control\" name=\"posId\" id=\"select_pos_search\" >\r\n");
      out.write("\t\t\t    </select>\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <button class=\"btn btn-primary\" id=\"emp_search_btn\">筛选</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--表格数据-->\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t<table class=\"table table-hover\" style='table-layout:fixed;' id=\"emps_table\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"check_all\">\r\n");
      out.write("\t\t\t\t\t\t</th> \r\n");
      out.write("\t\t\t\t\t\t\t<th>员工编号</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>姓名</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>性别</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>邮箱</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>部门</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>职位</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>描述</th>\r\n");
      out.write("\t\t\t\t\t\t\t<th>操作</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t<!-- 表格体 -->\r\n");
      out.write("\t\t\t\t\t</tbody>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--分页信息-->\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<!--分页文字信息-->\r\n");
      out.write("\t\t\t<div class=\"col-md-6\" id=\"page_info_area\"></div>\r\n");
      out.write("\t\t\t<!--分页条信息-->\r\n");
      out.write("\t\t\t<div class=\"col-md-6\" id=\"page_nav\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/asset/employee.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\"\r\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/bootstrap/js/bootstrap.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
