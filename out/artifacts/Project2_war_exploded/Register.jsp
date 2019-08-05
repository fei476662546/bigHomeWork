<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/17
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<HEAD>
    <%@ include file="/mkfriend/head.txt" %>
        <style type="text/css">
            #message{
                display: inline;
            }
        </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            //通过Jquery选择器定位username
            $(":input[name='logname']").change(function () {
                //获取Username的value值
                var val=$(this).val();//value有空格
                val=$.trim(val);//去掉空格
                //使用绝对路径
                var url="${pageContext.request.contextPath}/Register.do";
                //去掉缓存影响
                var args={"name":val,"time":new Date()};
                //ajax
                $.post(url,args,function(data){
                    // alert(data);
                    //使用空白div显示
                    $("#message").html(data);
                });
            });
            //window.onload事件
        });
    </script>
</HEAD>
<HTML>
<BODY bgcolor=cyan><Font size=2>
    <CENTER>
        <FORM action="${pageContext.request.contextPath}/helpRegister.do" name="form" method="post">
            <table>
                输入您的信息，会员名字必须由字母和数字组成，带*号项必须填写。
                <tr>
                    <td>会员名称:</td>
                    <td><Input type=text name="logname">*<div id="message"></div></td>
                </tr>
                <tr>
                    <td>设置密码:</td>
                    <td><Input type=password name="password">*</td>
                </tr>
                <tr>
                    <td>电子邮件:</td>
                    <td><Input type=text name="email"></td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td><Input type=text name="phone"></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><font> size=2>输入您的简历和交友标准：</font></td>
                </tr>
                <tr>
                    <td><TextArea name="message" Rows="6" Cols="30"></TextArea></td>
                </tr>
                <tr>
                    <td><Input type=submit name="g" value="提交"></td>
                </tr>
            </table>
        </Form>
    </CENTER>
</Font>
</Body>
</HTML>
