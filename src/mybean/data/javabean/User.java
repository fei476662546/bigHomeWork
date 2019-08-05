package mybean.data.javabean;

import java.util.Map;

/**
 * Create by mysteriousTime
 * time on 2019/7/19  10:18
 */
public class User {
    public static final int page_size=2;//每页记录数
    private String logname;//存储会员登录名字
    private String password;//存储会员登录密码
    private String phone;//存储会员会员的电话
    private String email;//存储会员的email地址
    private String message;//存储会员的简历
    private String pic;//存储会员照片文件的名字


    public User(String logname, String password, String phone, String email, String message) {
        this.logname = logname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.message = message;

    }



    public User(String logname, String password) {
        this.logname = logname;
        this.password = password;
    }

    public User(String logname) {
        this.logname=logname;
    }
public User(String logname,String phone,String email,String message){
        this.logname=logname;
        this.phone=phone;
        this.email=email;
        this.message=message;
}
    public User() {

    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "User{" +
                "logname='" + logname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
