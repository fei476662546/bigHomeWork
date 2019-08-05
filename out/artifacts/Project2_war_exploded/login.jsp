<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/17
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<HEAD><%@ include file="/mkfriend/head.txt" %>
   <style>
        #name{
            display: inline;
        }
   </style>

</HEAD>
<HTML>
<BODY bgcolor=pink><CENTER>
    <BR><BR>
    <FORM action="${pageContext.request.contextPath}/Login.do" Method="post">
    <table border=2>
        <tr> <th>请您登录</th></tr>
            <tr><td>登录名称:<Input type=text name="logname"></td></tr>
            <tr><td>输入密码:<Input type=password name="password"></td></tr>
    </table>
        <div id="name"></div>
    <BR><Input type=submit name="g" value="提交">
    </Form>
</CENTER>
</BODY>
</HTML>
