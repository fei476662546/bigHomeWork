package servive.impl;

import dao.impl.UserDaoImpl;
import mybean.data.javabean.User;
import servive.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/19  22:01
 */
public class UserServiceImpl implements UserService {
        UserDaoImpl userDao=new UserDaoImpl();
    @Override
    public boolean login(String logName, String pwd) {
        return userDao.findByNameAndPsd(logName,pwd)!=null?true:false;
    }

    @Override
    public int register(String sql, Object ...params) {
        return userDao.updateUser(sql,params);

    }
    public boolean findId(String user_logName,HttpServletResponse response) throws IOException {

        return userDao.findById(user_logName, response)!=null?false:true;
    }
    public List<User> findAll(){
        return userDao.getAllUsers();
    }

    @Override
    public boolean modifyPassword(User user) {
       return userDao.modifyPassword(user)==1?true:false;
    }

    @Override
    public boolean doModifyMess(User user) {
        return userDao.doModifyMess(user)==1?true:false;
    }

    @Override
    public List<User> getbyLogname(String select_name) {
        return userDao.getbyLogname(select_name);
    }

    @Override
    public List<User> findPage(int page) {//分页查询
        return userDao.findPage(page);

    }

    @Override
    public int findCount() {//总记录数
        return userDao.findCount();
    }


}
