package myservlet.control;

import mybean.data.javabean.User;
import servive.UserService;
import servive.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/17  16:46
 */
@WebServlet("/index")
public class TestServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int currPage=1;
            if (request.getParameter("page")!=null){//判断传递页码是否有效
                currPage=Integer.parseInt(request.getParameter("page"));
            }

    }

}


