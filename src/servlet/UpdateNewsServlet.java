package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BizDaoImpl.BizDaoImpl;
import enity.NewsInfo;

/**
 * Servlet implementation class UpdateNewsServlet
 */
public class UpdateNewsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String newsid = request.getParameter("newsid");
		String ntid=request.getParameter("ntid");
		String ntitle = request.getParameter("ntitle");
		String nauthor=request.getParameter("nauthor");
		String nsummary = request.getParameter("nsummary");
		String ncontent = request.getParameter("ncontent");
		NewsInfo ff= new NewsInfo(Integer.parseInt(newsid),Integer.parseInt(ntid),ntitle,nauthor,nsummary,ncontent,"");
		BizDaoImpl bdi = new BizDaoImpl();
		int flag=bdi.updateNews(ff);
		//获取登录名和密码
		HttpSession session= request.getSession();
		String name=(String)session.getAttribute("name");
		String pwd=(String)session.getAttribute("pwd");
		if(flag>0){
			//修改成功，进入admin/admin.jsp
			//js方式
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write("<script>");
			out.write("location.href='/zuoyePro/login?uname="+name+"&upwd="+pwd+"';");
			out.write("</script>");
			out.flush();
			out.close();
		}else{
			response.sendRedirect("error.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
