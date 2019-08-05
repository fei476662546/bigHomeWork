package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Create by mysteriousTime
 * time on 2019/7/22  15:04
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
//    public static final String login_page = "index.jsp";
//    //不被拦截的页面
//    public static ArrayList<String> initPages = new ArrayList<>();
//
//    static {
//        initPages.add("/index.jsp");
//        initPages.add("register.jsp");
//    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri.contains("/css") || uri.contains("/error") || uri.contains("/fonts")
                || uri.contains("/images") || uri.contains("/img") || uri.contains("/js")
                || uri.contains("/login.jsp") || uri.contains("Register.jsp")|| uri.contains("index.jsp")){
            chain.doFilter(req,resp);
        } else {
            //获取全局变量登录名，判断是否登录！
            Object user = request.getServletContext().getAttribute("logname");
            if (user == null){
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            } else {
                //已经登录
                chain.doFilter(req, resp);
            }
        }

//        chain.doFilter(req, resp);

        //        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        String currentURL = request.getRequestURI();
//        String ctxPath = request.getContextPath();
//        // 除掉项目名称时访问页面当前路径
//        String targetURL = currentURL.substring(ctxPath.length());
//        HttpSession session = request.getSession(false);
//        //判断是否为 不被拦截的界面
//        Boolean isInitPage = false;
//        for (int i = 0; i < initPages.size(); i++) {
//            String initPage = initPages.get(i);
//            System.out.print("\ninitPage:  " + initPage);
//            if (initPage.equals(targetURL)) {
//                isInitPage = true;
//            }
//        }
//
//        if (!isInitPage) {
//            System.out.println(
//                    "\ntargetURL:   " + targetURL + "\nctxPath:   " + ctxPath + "\ncurrentURL:   " + currentURL);
//            // 在不为登陆页面时，再进行判断，如果不是登陆页面也没有session则跳转到登录页面，
//            if (session == null || session.getAttribute("user") == null) {
//                response.sendRedirect(ctxPath+"/login.jsp");
//                return;
//            } else {
//                // 这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
//                chain.doFilter(request, response);
//                return;
//            }
//        } else {
//            chain.doFilter(request, response);
//
//        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
