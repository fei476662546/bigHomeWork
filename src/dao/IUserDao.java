package dao;

import mybean.data.javabean.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/19  10:17
 */
public interface IUserDao {
    default void saveUser(User user){};
    void deleteUser(String user_logName);
    int updateUser(String sql,Object...params);
    int modifyPassword(User user);
    int doModifyMess(User user);
    List<User> getAllUsers();//查询所有
    User findById(String user_logName, HttpServletResponse response) throws IOException;
    User findByNameAndPsd(String name,String pwd);
   List<User> getbyLogname(String select_name);
   int UploadFile(String sql,String path);//上传图片
    List<User> findPage(int page);//分页查询所有商品信息
    int findCount() ;//所有记录

}
