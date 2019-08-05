package myservlet.control;


import dao.impl.UserDaoImpl;
import factory.UserFactory;
import servive.impl.UserServiceImpl;
import util.UploadUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Create by mysteriousTime
 * time on 2019/7/20  18:38
 */
@WebServlet(name="HandleUpload")
@MultipartConfig
public class HandleUpload extends HttpServlet {
    UserDaoImpl userDao;
    public HandleUpload() {
        userDao = UserFactory.getInstance("userDao", UserDaoImpl.class);
    }
    private static final long serialVersionUID = 5661013723204858883L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取文件上传组件
        Part part = request.getPart("file");
// 获取文件的路径
        String header = part.getHeader("content-disposition");
        String path = header.substring(header.indexOf("filename=") + 10, header.length() - 1);
// 获取文件名
        String name = UploadUtils.getRealName(path);
// 获取文件的存放目录
        String dir = UploadUtils.getDir(name);
        String realPath = this.getServletContext().getRealPath("/img" + dir);
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
// 对拷流
        InputStream inputStream = part.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(new File(file, name));
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        // 存入数据库

        // 设置图片存储的虚拟路径
        String virtualPath = "img/" + name;
        System.out.println(virtualPath);
        // GeneralDao.update() 为自己写的函数，采用预编译，可以根据自己的来
        String logname= (String) request.getServletContext().getAttribute("logname");
        UserDaoImpl userDao=new UserDaoImpl();
        int ok = userDao.UploadFile(logname,virtualPath);
        if (ok == 1) {
            System.out.println("存入数据库成功，虚拟路径为：" + virtualPath);
        } else {
            System.out.println("存入数据库失败 ！");
        } // if (ok ==
// 关闭资源
        outputStream.close();
        inputStream.close();
        /*-----------------------------------------------------------------*/


// 删除临时文件
        part.delete();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("文件" + name + "上传成功！");
        /*--------------------------------------------------------------------------------*/


        response.getOutputStream().flush();
        response.getOutputStream().close();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
