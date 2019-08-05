package myservlet.control;

import com.sun.org.apache.regexp.internal.RE;
import dao.impl.UserDaoImpl;
import factory.UserFactory;
import mybean.data.javabean.UploadFile;
import mybean.data.javabean.User;
import servive.impl.UserServiceImpl;
import util.UploadUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/22  16:17
 */
@WebServlet("*.do")
@MultipartConfig(location = "F:\\idea\\workspace\\Project2\\web\\img")
public class SuperServlet extends HttpServlet {
    UserDaoImpl userDao;
    UserServiceImpl userService = new UserServiceImpl();

    public SuperServlet() {
        userDao = UserFactory.getInstance("userDao", UserDaoImpl.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.contains("Login")) {
            doLogin(request, response);
        }
        if (path.contains("Register")) {
            doRegister(request, response);
        }
        if (path.contains("ModifyPassword")) {
            doModifyPassword(request, response);
        }
        if (path.contains("ModifyMess")) {
            doModifyMess(request, response);
        }
        if (path.contains("HandleUpload")) {
            doUpload(response, request);
        }
        if (path.contains("helpShowMember")) {
            dohelpShowMember(request, response);
        }
//        if (path.contains("ChoiceLookType")) {
//            doChoiceLookType(request, response);
//        }
    }

    /*
     * 登录方法
     * */
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String name = request.getParameter("logname");
        String pwd = request.getParameter("password");
        PrintWriter out = response.getWriter();
        if (userService.login(name, pwd) == true) {
            request.getServletContext().setAttribute("logname", name);
            request.getServletContext().setAttribute("pwd", pwd);
            request.getSession().setAttribute("name", name);
//            System.out.println("xxxxxx" + request.getContextPath());
            doChoiceLookType(request, response);
            response.sendRedirect(request.getContextPath() + "/showLoginMess.jsp");

        } else {
            out.print("用户名密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        out.flush();
        out.close();
    }

    /*
     * 注册方法
     * */
    protected void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logname = request.getParameter("logname");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        PrintWriter out = response.getWriter();

        if (userService.findId(logname, response) == true) {
            User user = new User(logname, pwd, email, phone, message);
            String Register_sql = "insert into member(logname,password,email,phone,message) values(?,?,?,?,?)";
            if (userService.register(Register_sql, logname, pwd, email, phone, message) > 0) {
                System.out.println("注册成功！");
                request.getSession().setAttribute("name", logname);
                request.getSession().setAttribute("password", pwd);
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("phone", phone);
                request.getSession().setAttribute("message", message);

                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                System.out.println("注册失败！");
            }
        } else {
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }

    }

    /*
     *
     * 修改密码方法
     * */
    protected void doModifyPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String oldpwd = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String oldPassword = (String) request.getServletContext().getAttribute("pwd");//从登录界面取出密码
        if (oldPassword.equals(oldpwd)) {
            String logname = (String) request.getServletContext().getAttribute("logname");
            User user = new User(logname, newPassword);
            userService.modifyPassword(user);
            response.sendRedirect(request.getContextPath() + "/showLoginMess.jsp");
        } else {
            out.print("密码错误");
        }


    }

    /*
     * 显示用户所有信息
     * */
    protected void doChoiceLookType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // String name = (String) request.getServletContext().getAttribute("logname");
        List<User> list = userService.findAll();
        request.getSession().setAttribute("list", list);
        out.print(list);
        System.out.println(list);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/choiceLookType.jsp");
//        response.sendRedirect(request.getContextPath() + "/choiceLookType.jsp");

    }

    protected void doModifyMess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String logname = (String) request.getServletContext().getAttribute("logname");
        User user = new User(logname, phone, email, message);
        if (logname.length() != 0) {
            request.getSession().setAttribute("phone", phone);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("message", message);
            userService.doModifyMess(user);
            System.out.println("修改用户信息成功！");
            response.sendRedirect(request.getContextPath() + "/showModifyMess.jsp");
        } else {
            out.print("修改信息失败");
        }


    }

    private void doUpload(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String logname = (String) request.getServletContext().getAttribute("logname");
        response.setContentType("html/text;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //存到服务器路径下的img，Project2_war_exploded/HandleUpload.do/img
        String dir = request.getServletContext().getRealPath("img");
        //获取从jsp页面传来的name：file
        Part part = request.getPart("file");
        String header = part.getHeader("content-disposition");
        //获得文件名
        String filename = UploadUtils.getFileName(header);
        //获得文件的绝对路径
        String Path = dir + File.separator + filename;
        if (filename != null && !filename.isEmpty()) {//如果文件不为空
            //写入指定的文件夹
            part.write(Path);
        }
        //将文件路径存到数据库
        int ok = userDao.UploadFile(logname, filename);
        if (ok != 0) {
            System.out.println("存入数据库成功，路径为：" + Path);
            System.out.println("fileName:" + filename);
            request.getSession().setAttribute("fileName", filename);
            request.getRequestDispatcher("/showUploadMess.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute("message", "上传图片有误");
            out.print("上传失败！");
            System.out.println("存入数据库失败 ！");
        }

    }

    protected void dohelpShowMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int currPage = 1;
        int pages;//总页数
        int count = userService.findCount();//查询总记录数
        if (count % User.page_size == 0) {
            pages = count / User.page_size;
        } else {
            pages = count / User.page_size + 1;//对总页数赋值
        }
        if (request.getParameter("currPage") != null) {//判断传递页码是否有效
            if (currPage <= 0) {
                currPage = 1;
            }
            if (currPage > pages) {
                currPage = pages;
            }
            currPage = Integer.parseInt(request.getParameter("currPage"));//对当前页码赋值
        }
        if (request.getParameter("currPagelogname") != null) {
            doList(request, response);
        } else {
            request.setAttribute("pages", pages);//一共几页
            request.setAttribute("page_size", User.page_size);//每页几条记录
            List<User> list = userService.findPage(currPage);//查询当前页数信息
            request.setAttribute("currPage", currPage);//当前页码
            request.setAttribute("currlist", list);//当前页码的信息
            request.getRequestDispatcher("showAllMember.jsp").
                    forward(request, response);
        }
    }

    protected void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("currPagelogname");
        if (name != null) {
            if (userService.findId(name, response) == true) {
                List<User> list = userService.getbyLogname(name);
                request.setAttribute("pageList", list);
                request.getRequestDispatcher("choiceLookType.jsp");
            } else {
                out.print("您输入的用户不存在！");
            }
        }
    }
}



