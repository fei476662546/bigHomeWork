package servive;

import mybean.data.javabean.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/19  22:12
 */
public interface UserService {
    boolean login (String logName,String pwd);
    int register(String sql,Object...params);
    boolean findId(String user_logName, HttpServletResponse response) throws IOException;
    List<User> findAll();
    boolean modifyPassword(User user);
    boolean doModifyMess(User user);
    List<User>getbyLogname(String select_name);
    List<User> findPage(int page);//分页查询所有分页信息
   int findCount() ;//所有记录

}
