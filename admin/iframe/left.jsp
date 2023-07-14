<%@ page language="java" import="java.util.*" contentType="text/html;charset=gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript" src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/laydate/laydate.js"></script>
</HEAD>
<%
	String username=(String)session.getAttribute("user");  String sf=(String)session.getAttribute("sf");  
	if(username==null){
		response.sendRedirect(path+"index.jsp");
	}
	else{ 
%>
<body>
<div class="left">
     
<script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>

<div id="my_menu" class="sdmenu">

	<div class="collapsed">
		<span>密码信息管理</span>
		<a href="<%=basePath %>admin/system/editpwd.jsp" target="MainFrame">密码信息管理</a> 
	</div>
	<%if(sf.equals("系统管理员")){ %>
	<div class="collapsed">
		<span>系统用户管理</span>
		<a href="<%=basePath %>admin/system/index.jsp" target="MainFrame">系统用户管理</a> 
		<a href="<%=basePath %>admin/system/add.jsp?method=addm" target="MainFrame">增加系统用户</a> 
		 <a href="<%=basePath %>admin/system/s.jsp" target="MainFrame">系统用户查询</a>  
	</div>
	
	 
	<div class="collapsed">
		<span>老人信息管理</span>
		<a href="<%=basePath %>admin/lr/index.jsp" target="MainFrame">老人信息管理</a> 
		<a href="<%=basePath %>admin/lr/add.jsp?method=addlr" target="MainFrame">增加老人信息</a>  
		 <a href="<%=basePath %>admin/lr/s.jsp" target="MainFrame">老人信息查询</a>  
	</div>
	<div class="collapsed">
		<span>事故记录管理</span>
		 <a href="<%=basePath %>admin/sg/index.jsp" target="MainFrame">事故记录管理</a> 
		<a href="<%=basePath %>admin/sg/add.jsp?method=addsg" target="MainFrame">增加事故记录</a> 
		 <a href="<%=basePath %>admin/sg/s.jsp" target="MainFrame">事故记录查询</a> 
	</div>  
	<div class="collapsed">
		<span>请假信息管理</span>
		<a href="<%=basePath %>admin/qj/index.jsp" target="MainFrame">请假信息管理</a> 
		<a href="<%=basePath %>admin/qj/add.jsp?method=addqj" target="MainFrame">增加请假信息</a> 
		 <a href="<%=basePath %>admin/qj/s.jsp" target="MainFrame">请假信息查询</a>  
	</div>
	<div class="collapsed">
		<span>床位分配管理</span>
		<a href="<%=basePath %>admin/cw/index.jsp" target="MainFrame">床位分配管理</a>  
		 <a href="<%=basePath %>admin/cw/s.jsp" target="MainFrame">床位信息查询</a>  
	</div> 
	
	<div class="collapsed">
		<span>入住费用管理</span>
		<a href="<%=basePath %>admin/fy/index.jsp" target="MainFrame">入住费用管理</a> 
		<a href="<%=basePath %>admin/fy/add.jsp?method=addfy" target="MainFrame">增加入住费用</a> 
		 <a href="<%=basePath %>admin/fy/s.jsp" target="MainFrame">入住费用查询</a>  
	</div> 
	<div class="collapsed">
		<span>护工薪资管理</span>
		<a href="<%=basePath %>admin/xz/index.jsp" target="MainFrame">护工薪资管理</a> 
		<a href="<%=basePath %>admin/xz/add.jsp?method=addxz" target="MainFrame">增加护工薪资</a> 
	</div> 
	<div class="collapsed">
		<span>护工请假管理</span>
		<a href="<%=basePath %>admin/hgqj/index.jsp" target="MainFrame">护工请假管理</a> 
		<a href="<%=basePath %>admin/hgqj/add.jsp?method=addhgqj" target="MainFrame">增加护工请假</a> 
		 <a href="<%=basePath %>admin/hgqj/s.jsp" target="MainFrame">护工请假查询</a>  
	</div>
	<%}else{ %> 
	 <div class="collapsed">
		<span>老人信息查看</span>
		<a href="<%=basePath %>admin/lr/index2.jsp" target="MainFrame">老人信息查看</a>  
		 <a href="<%=basePath %>admin/lr/s2.jsp" target="MainFrame">老人信息查询</a>  
	</div>
	<div class="collapsed">
		<span>请假信息查看</span>
		<a href="<%=basePath %>admin/hgqj/index2.jsp" target="MainFrame">请假信息查看</a>  
	</div>
 	<div class="collapsed">
		<span>护工薪资查看</span>
		<a href="<%=basePath %>admin/xz/index2.jsp" target="MainFrame">护工薪资查看</a>  
	</div> 
	<%} %>
 	<div class="collapsed">
		<span>注销退出系统</span>
		<a onclick="if (confirm('确定要退出吗？')) return true; else return false;" href="<%=basePath %>AdminServlet?method=adminexit" target="_top" >注销退出系统</a>
	</div> 
</div>
     </div>
     <div class="Switch"></div>
     <script type="text/javascript">
	$(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
	 
		});
});
</script> 
</body>
<%} %>

</html>
