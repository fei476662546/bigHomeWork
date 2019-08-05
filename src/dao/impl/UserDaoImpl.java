package dao.impl;

import dao.IUserDao;
import factory.UserFactory;
import mybean.data.javabean.User;
import util.JDBCUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/19  21:53
 */
public class UserDaoImpl implements IUserDao {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public UserDaoImpl() {
        conn = JDBCUtil.getConn();
    }

    @Override
    public void deleteUser(String user_logName) {

    }

    @Override
    public int updateUser(String sql, Object... params) {
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject((i + 1), params[i]);
            }
            result = ps.executeUpdate();//执行sql语句

        } catch (SQLException e) {
            System.out.println("执行更新数据库方法失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public int modifyPassword(User user) {//修改密码
        int result = 0;
        try {
            String sql = "update member set password=? where logname=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getLogname());
            boolean flag = ps.execute();
            if (flag == true) {
                System.out.println("修改密码成功！");
                result = 1;
            }
        } catch (SQLException e) {
            System.out.println("修改密码失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public int doModifyMess(User user) {//更改用户信息
        String sql = "update member set phone=?,email=?,message=? where logname=?";
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMessage());
            ps.setString(4, user.getLogname());
            if (ps.execute() == true) {
                System.out.println("修改用户信息成功！");
                result = 1;
            }

        } catch (SQLException e) {
            System.out.println("更改用户信息失败" + e.getMessage());
        }
        return result;
    }


    @Override
    public List<User> getAllUsers() {//查询所有元素
        List<User> list = new ArrayList<>();
        String select_sql = "select * from member ";
        try {
            ps = conn.prepareStatement(select_sql);
            rs = ps.executeQuery();
            //得到结果集的元数据
//            ResultSetMetaData rm = rs.getMetaData();
//            //得到列的数目
//            int count = rm.getColumnCount();
            while (rs.next()) {
                    User user = new User();
                    user.setLogname(rs.getString("logname"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setMessage(rs.getString("message"));
                    user.setPic(rs.getString("pic"));
                    list.add(user);
                }

//                Map<String, Object> map = new HashMap<>();
//                for (int i = 1; i <= count; i++) {
//                    //不同的字段值,rs.getxxx(i) i是从1开始
//                    map.put(rm.getColumnLabel(i), rs.getObject(i));
//                }
//                list.add(map);
        } catch (SQLException e) {
            System.out.println("查询所有失败：" + e.getMessage());
        }
        return list;
    }

    @Override
    public User findById(String user_logName, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String sql = "select * from member where logname=?";
        String result = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_logName);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = "<font color='red'>该用户已被注册，请重新注册</font>";
                System.out.println("11111111111111111111111");

                out.print(result);
                out.flush();
                User user = new User(rs.getString("logname"));
                return user;
            } else {
                result = "<font color='blue'>该用户可以使用</font>";
            }
            out.print(result);
        } catch (SQLException e) {
            System.out.println("查询用户名失败！" + e.getMessage());
        }
        return null;
    }

    @Override
    public User findByNameAndPsd(String name, String pwd) {
        String select_sql = "select * from member where logname=? and password=?";
        try {

            ps = conn.prepareStatement(select_sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("logname"), rs.getString("password"));
                System.out.println("用户名密码正确");
                return user;
            } else {
                System.out.println("用户名密码错误");
            }
        } catch (SQLException e) {
            System.out.println("结果集获取失败" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getbyLogname(String select_name) {//查询所有元素
        List<User> list = new ArrayList<>();
        String select_sql = "select * from member where logname=?";
        try {
            ps = conn.prepareStatement(select_sql);
            ps.setString(1, select_name);
            rs = ps.executeQuery();
            //得到结果集的元数据
            while (rs.next()) {
                User user = new User();
                user.setLogname(rs.getString("logname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setMessage(rs.getString("message"));
                user.setPic(rs.getString("pic"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("根据用户名查询所有信息失败：" + e.getMessage());
            return null;
        }


    }

    @Override
    public int UploadFile(String Logname,String path ) {
        int result = 0;
        String sql ="update member set pic= ? where logname=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,path);
            ps.setString(2,Logname);
            if(ps.executeUpdate()!=0) {

                result = ps.executeUpdate();
            }
            System.out.println("图片路径上传数据库成功");

        } catch (SQLException e) {
            System.out.println("图片上传数据库失败" + e.getMessage());
            return 0;
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<User> findPage(int page) {//分页管理所有商品信息
        List<User> list=new ArrayList<>();
        String sql="select * from member order by logname limit ?,?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(page-1)*User.page_size);
            ps.setInt(2,User.page_size);
            rs=ps.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setLogname(rs.getString("logname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setMessage(rs.getString("message"));
                user.setPic(rs.getString("pic"));
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println("分页管理当前页数的信息失败"+e.getMessage());
        }
        System.out.println("当前页数："+list);
        return list;
    }

    @Override
    public int findCount()  {//查询总记录数
        int count =0;
        String sql="select count(*) from member";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);//对总记录赋值
            }
        } catch (SQLException e) {
            System.out.println("查询所有记录失败"+e.getMessage());
        }
        System.out.println("总记录为："+count);
        return count;
    }
//    public L findById(String sql) {
//        String result = "";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, user_logName);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                result= "<font color='red'>该用户已被注册，请重新注册</font>";
//                System.out.println("11111111111111111111111");
//
//
//                User user = new User(rs.getString("logname"));
//                return user;
//            } else {
//                result = "<font color='blue'>该用户可以使用</font>";
//            }
//
//        } catch (SQLException e) {
//            System.out.println("查询用户名失败！" + e.getMessage());
//        }
//        return null;
//    }
}
