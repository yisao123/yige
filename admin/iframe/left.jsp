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
		<span>������Ϣ����</span>
		<a href="<%=basePath %>admin/system/editpwd.jsp" target="MainFrame">������Ϣ����</a> 
	</div>
	<%if(sf.equals("ϵͳ����Ա")){ %>
	<div class="collapsed">
		<span>ϵͳ�û�����</span>
		<a href="<%=basePath %>admin/system/index.jsp" target="MainFrame">ϵͳ�û�����</a> 
		<a href="<%=basePath %>admin/system/add.jsp?method=addm" target="MainFrame">����ϵͳ�û�</a> 
		 <a href="<%=basePath %>admin/system/s.jsp" target="MainFrame">ϵͳ�û���ѯ</a>  
	</div>
	
	 
	<div class="collapsed">
		<span>������Ϣ����</span>
		<a href="<%=basePath %>admin/lr/index.jsp" target="MainFrame">������Ϣ����</a> 
		<a href="<%=basePath %>admin/lr/add.jsp?method=addlr" target="MainFrame">����������Ϣ</a>  
		 <a href="<%=basePath %>admin/lr/s.jsp" target="MainFrame">������Ϣ��ѯ</a>  
	</div>
	<div class="collapsed">
		<span>�¹ʼ�¼����</span>
		 <a href="<%=basePath %>admin/sg/index.jsp" target="MainFrame">�¹ʼ�¼����</a> 
		<a href="<%=basePath %>admin/sg/add.jsp?method=addsg" target="MainFrame">�����¹ʼ�¼</a> 
		 <a href="<%=basePath %>admin/sg/s.jsp" target="MainFrame">�¹ʼ�¼��ѯ</a> 
	</div>  
	<div class="collapsed">
		<span>�����Ϣ����</span>
		<a href="<%=basePath %>admin/qj/index.jsp" target="MainFrame">�����Ϣ����</a> 
		<a href="<%=basePath %>admin/qj/add.jsp?method=addqj" target="MainFrame">���������Ϣ</a> 
		 <a href="<%=basePath %>admin/qj/s.jsp" target="MainFrame">�����Ϣ��ѯ</a>  
	</div>
	<div class="collapsed">
		<span>��λ�������</span>
		<a href="<%=basePath %>admin/cw/index.jsp" target="MainFrame">��λ�������</a>  
		 <a href="<%=basePath %>admin/cw/s.jsp" target="MainFrame">��λ��Ϣ��ѯ</a>  
	</div> 
	
	<div class="collapsed">
		<span>��ס���ù���</span>
		<a href="<%=basePath %>admin/fy/index.jsp" target="MainFrame">��ס���ù���</a> 
		<a href="<%=basePath %>admin/fy/add.jsp?method=addfy" target="MainFrame">������ס����</a> 
		 <a href="<%=basePath %>admin/fy/s.jsp" target="MainFrame">��ס���ò�ѯ</a>  
	</div> 
	<div class="collapsed">
		<span>����н�ʹ���</span>
		<a href="<%=basePath %>admin/xz/index.jsp" target="MainFrame">����н�ʹ���</a> 
		<a href="<%=basePath %>admin/xz/add.jsp?method=addxz" target="MainFrame">���ӻ���н��</a> 
	</div> 
	<div class="collapsed">
		<span>������ٹ���</span>
		<a href="<%=basePath %>admin/hgqj/index.jsp" target="MainFrame">������ٹ���</a> 
		<a href="<%=basePath %>admin/hgqj/add.jsp?method=addhgqj" target="MainFrame">���ӻ������</a> 
		 <a href="<%=basePath %>admin/hgqj/s.jsp" target="MainFrame">������ٲ�ѯ</a>  
	</div>
	<%}else{ %> 
	 <div class="collapsed">
		<span>������Ϣ�鿴</span>
		<a href="<%=basePath %>admin/lr/index2.jsp" target="MainFrame">������Ϣ�鿴</a>  
		 <a href="<%=basePath %>admin/lr/s2.jsp" target="MainFrame">������Ϣ��ѯ</a>  
	</div>
	<div class="collapsed">
		<span>�����Ϣ�鿴</span>
		<a href="<%=basePath %>admin/hgqj/index2.jsp" target="MainFrame">�����Ϣ�鿴</a>  
	</div>
 	<div class="collapsed">
		<span>����н�ʲ鿴</span>
		<a href="<%=basePath %>admin/xz/index2.jsp" target="MainFrame">����н�ʲ鿴</a>  
	</div> 
	<%} %>
 	<div class="collapsed">
		<span>ע���˳�ϵͳ</span>
		<a onclick="if (confirm('ȷ��Ҫ�˳���')) return true; else return false;" href="<%=basePath %>AdminServlet?method=adminexit" target="_top" >ע���˳�ϵͳ</a>
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
