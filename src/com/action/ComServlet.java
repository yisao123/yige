package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		
		
		if(method.equals("addlr")){  //老人信息   
			//String xm="";String xb="";String nl="";String st="";String hj="";String sfz="";String lxr="";
			//String dh="";String zz="";String cw="";String hg="";String rz="";String cy="";
			String xm = request.getParameter("xm"); 
			String xb = request.getParameter("xb"); 
			String nl = request.getParameter("nl"); 
			String st = request.getParameter("st"); 
			String hj = request.getParameter("hj"); 
			String sfz = request.getParameter("sfz");  
			String lxr = request.getParameter("lxr");  
			String dh = request.getParameter("dh");  
			String zz = request.getParameter("zz");  
			String cw = request.getParameter("cw");  
			String hg = request.getParameter("hg");  
			String rz = request.getParameter("rz");  
			String cy = request.getParameter("cy");   
			int flag = cBean.comUp("insert into lr(xm,xb,nl,st,hj,sfz,lxr,dh,zz,cw,hg,rz,cy)  " +
					"values('"+xm+"','"+xb+"','"+nl+"','"+st+"','"+hj+"','"+sfz+"','"+lxr+"','"+dh+"','"+zz+"','"+cw+"','"+hg+"','"+rz+"','"+cy+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uplr")){ //修改老人信息 
			String id=request.getParameter("id");
			String xm = request.getParameter("xm"); 
			String xb = request.getParameter("xb"); 
			String nl = request.getParameter("nl"); 
			String st = request.getParameter("st"); 
			String hj = request.getParameter("hj"); 
			String sfz = request.getParameter("sfz");  
			String lxr = request.getParameter("lxr");  
			String dh = request.getParameter("dh");  
			String zz = request.getParameter("zz");  
			String cw = request.getParameter("cw");  
			String hg = request.getParameter("hg");  
			String rz = request.getParameter("rz");  
			String cy = request.getParameter("cy");     
			int flag = cBean.comUp("update lr set xm='"+xm+"',xb='"+xb+"',nl='"+nl+"',st='"+st+"',hj='"+hj+"',sfz='"+sfz+"'" +
					",lxr='"+lxr+"',dh='"+dh+"',zz='"+zz+"',cw='"+cw+"',hg='"+hg+"',rz='"+rz+"',cy='"+cy+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("dellr")){//删除老人信息
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from lr where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/lr/index.jsp").forward(request, response);
			}
		} 
		
		else if(method.equals("addsg")){  //事故记录    
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");  
			int flag = cBean.comUp("insert into sg(lr,sj,xx) values('"+lr+"','"+sj+"','"+xx+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upsg")){ //修改事故记录 
			String id=request.getParameter("id");
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");      
			int flag = cBean.comUp("update sg set lr='"+lr+"',sj='"+sj+"',xx='"+xx+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delsg")){//删除事故记录
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from sg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/sg/index.jsp").forward(request, response);
			}
		} 
		
		else if(method.equals("addqj")){  //请假信息    
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");  
			int flag = cBean.comUp("insert into qj(lr,sj,xx) values('"+lr+"','"+sj+"','"+xx+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upqj")){ //修改请假信息 
			String id=request.getParameter("id");
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");      
			int flag = cBean.comUp("update qj set lr='"+lr+"',sj='"+sj+"',xx='"+xx+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delqj")){//删除请假信息
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from qj where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/qj/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("upcw")){  
			String id=request.getParameter("id");
			String cw = request.getParameter("cw");     
			int flag = cBean.comUp("update lr set cw='"+cw+"'  where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cw/index.jsp").forward(request, response);
			}  
		} 
		
		
		else if(method.equals("addfy")){  //入住费用    
			String lr = request.getParameter("lr"); 
			String fy = request.getParameter("fy"); 
			String kc = request.getParameter("kc"); 
			String zt = request.getParameter("zt"); 
			String xx = request.getParameter("xx");  
			float sji=Float.parseFloat(fy)-Float.parseFloat(kc);
			int flag = cBean.comUp("insert into fy(lr,fy,kc,zt,xx,sji) values('"+lr+"','"+fy+"','"+kc+"','"+zt+"','"+xx+"','"+sji+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upfy")){ //修改入住费用 
			String id=request.getParameter("id");
			String lr = request.getParameter("lr"); 
			String fy = request.getParameter("fy"); 
			String kc = request.getParameter("kc"); 
			String zt = request.getParameter("zt"); 
			String xx = request.getParameter("xx"); 
			float sji=Float.parseFloat(fy)-Float.parseFloat(kc);
			int flag = cBean.comUp("update fy set lr='"+lr+"',fy='"+fy+"',kc='"+kc+"',zt='"+zt+"',xx='"+xx+"',sji='"+sji+"'  where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delfy")){//删除入住费用
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from fy where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/fy/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("addxz")){  //入住费用    
			String lr = request.getParameter("lr"); 
			String fy = request.getParameter("fy"); 
			String kc = request.getParameter("kc"); 
			String zt = request.getParameter("zt"); 
			String xx = request.getParameter("xx");  
			float sji=Float.parseFloat(fy)-Float.parseFloat(kc);
			int flag = cBean.comUp("insert into xz(lr,fy,kc,zt,xx,sji) values('"+lr+"','"+fy+"','"+kc+"','"+zt+"','"+xx+"','"+sji+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upxz")){ //修改入住费用 
			String id=request.getParameter("id");
			String lr = request.getParameter("lr"); 
			String fy = request.getParameter("fy"); 
			String kc = request.getParameter("kc"); 
			String zt = request.getParameter("zt"); 
			String xx = request.getParameter("xx"); 
			float sji=Float.parseFloat(fy)-Float.parseFloat(kc);
			int flag = cBean.comUp("update xz set lr='"+lr+"',fy='"+fy+"',kc='"+kc+"',zt='"+zt+"',xx='"+xx+"',sji='"+sji+"'  where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delxz")){//删除入住费用
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/xz/index.jsp").forward(request, response);
			}
		} 
		
		
		
		else if(method.equals("addhgqj")){  //请假信息    
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");  
			int flag = cBean.comUp("insert into hgqj(lr,sj,xx) values('"+lr+"','"+sj+"','"+xx+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uphgqj")){ //修改请假信息 
			String id=request.getParameter("id");
			String lr = request.getParameter("lr"); 
			String sj = request.getParameter("sj"); 
			String xx = request.getParameter("xx");      
			int flag = cBean.comUp("update hgqj set lr='"+lr+"',sj='"+sj+"',xx='"+xx+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delhgqj")){//删除请假信息
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hgqj where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hgqj/index.jsp").forward(request, response);
			}
		} 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
