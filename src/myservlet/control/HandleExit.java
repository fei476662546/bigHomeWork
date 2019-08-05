package myservlet.control;



/**
 * Create by mysteriousTime
 * time on 2019/7/20  19:22
 */
import mybean.data.*;
import mybean.data.javabean.Login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class HandleExit extends HttpServlet
{ public void init(ServletConfig config) throws ServletException
{
    super.init(config);
}
    public  void  doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {  HttpSession session=request.getSession(true);
        Login login=(Login)session.getAttribute("login");
        boolean ok=true;
        if(login==null)
        {
            ok=false;
            response.sendRedirect("login.jsp");
        }
        if(ok==true)
        {
            continueDoPost(request,response);
        }
    }
    public void continueDoPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        HttpSession session=request.getSession(true);
        session.invalidate();              //销毁用户的session对象
        response.sendRedirect("index.jsp"); //返回主页
    }
    public  void  doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        doPost(request,response);
    }
}
