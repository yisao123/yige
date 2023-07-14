<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		response.sendRedirect(basePath+"index.jsp");
	}
	else{
		String method=request.getParameter("method");
		String id="";
		String xm="";String xb="";String nl="";String st="";String hj="";String sfz="";String lxr="";
		String dh="";String zz="";String cw="";String hg="";String rz="";String cy="";
		if(method.equals("uplr")){
			id=request.getParameter("id");
			List alist=cb.get1Com("select * from lr where id='"+id+"'",14);
			xm=alist.get(1).toString();
			xb=alist.get(2).toString();
			nl=alist.get(3).toString();
			st=alist.get(4).toString();
			hj=alist.get(5).toString();
			sfz=alist.get(6).toString();
			lxr=alist.get(7).toString(); 
			dh=alist.get(8).toString();  
			zz=alist.get(9).toString();  
			cw=alist.get(10).toString();  
			hg=alist.get(11).toString();  
			rz=alist.get(12).toString();  
			cy=alist.get(13).toString();   
		}
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>老人信息管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>ComServlet?method=<%=method%>&id=<%=id%>" method="post" name="form1">
<table class="table table-bordered"> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">姓名：</td>
     <td><input type="text" name="xm" class="span4" value="<%=xm %>" required/></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">性别：</td>
     <td><input type="radio" name="xb" value="男" checked="checked"/> 男 <input type="radio" name="xb" value="女"/> 女</td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">年龄：</td>
     <td><input type="number" name="nl" class="span4" value="<%=nl %>" required/></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">身体状况：</td>
     <td><input type="text" name="st" class="span4" value="<%=st %>" required /></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">户籍：</td>
     <td><input type="text" name="hj" class="span4" value="<%=hj %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">身份证：</td>
     <td><input type="text" name="sfz" class="span4" value="<%=sfz %>" pattern="[0-9]{18}" title="18位身份证号码" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系人：</td>
     <td><input type="text" name="lxr" class="span4" value="<%=lxr %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
     <td><input type="text" name="dh" class="span4" value="<%=dh %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">家庭住址：</td>
     <td><input type="text" name="zz" class="span4" value="<%=zz %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">床位号：</td> 
     <td><input type="text" name="cw" class="span4" value="<%=cw %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">护工编号：</td>
     <td><select name="hg">
    <%if(method.equals("uplr")){ %><option value="<%=hg%>"><%=hg%></option> <%} %> 
    <%List flist=cb.getCom("select * from admin where sf='普通管理员' order by id desc",2);if(!flist.isEmpty()){for(int i=0;i<flist.size();i++){List list2=(List)flist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">入住日期：</td>
     <td><input type="date" name="rz" class="span4" value="<%=rz %>" required/></td> 
     </tr> 
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">出院日期：</td>
     <td><input type="date" name="cy" class="span4" value="<%=cy %>" required/></td> 
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