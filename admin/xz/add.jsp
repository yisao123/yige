<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>  
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript" src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/laydate/laydate.js"></script>

<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath %>editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath %>editor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=basePath %>editor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '<%=basePath %>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath %>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath %>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message"); 
	
	String username=(String)session.getAttribute("user"); 
	if(username==null){
		response.sendRedirect(path+"index.jsp");
	}
	else{ 
		String method=request.getParameter("method");  
		String id="";String lr="";String fy="";String kc="";String zt="";String xx=""; 
		if(method.equals("upxz")){
			id=request.getParameter("id");
			List jlist = cb.get1Com("select * from xz where id='"+id+"'",6);
			lr=jlist.get(1).toString();
			fy=jlist.get(2).toString();  
			kc=jlist.get(3).toString();  
			zt=jlist.get(4).toString();  
			xx=jlist.get(5).toString();  
		}	  
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>护工薪资管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>ComServlet" method="post" name="form1">
<table class="table table-bordered"> 
     <tr><input type="hidden" name="method" value="<%=method%>" /><input type="hidden" name="id" value="<%=id%>" />
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">护工：</td>
     <td><select name="lr">
    <%if(method.equals("upfy")){ %><option value="<%=lr%>"><%=lr%></option> <%} %> 
    <%List flist=cb.getCom("select * from admin where sf='普通管理员' order by id desc",2);if(!flist.isEmpty()){for(int i=0;i<flist.size();i++){List list2=(List)flist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></td> 
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">工资：</td>
     <td><input type="number" name="fy" class="span4" value="<%=fy %>" required/></td> 
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">扣除费用：</td>
     <td><input type="number" name="kc" class="span4" value="<%=kc %>" required/></td> 
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">发放状态：</td>
     <td><select name=zt>
      <%if(method.equals("upfy")){ %><option value="<%=zt%>"><%=zt%></option> <%} %> 
        <option value="已发">已发</option>
        <option value="未发">未发</option> 
        </select></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">详细信息：</td>
     <td><textarea name="xx" class="span4" rows="6" required><%=xx%></textarea></td> 
     </tr> 
     <tr>
     	<td class="text-center" colspan="2"><input type="submit" value="确定" class="btn btn-info  " style="width:80px;" /></td>
     </tr>
     </table> 
</form>
   </div>  
 </div>  
</body>
<%} %>