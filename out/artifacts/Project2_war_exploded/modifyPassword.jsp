<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<HEAD><%@ include file="/mkfriend/head.txt" %></HEAD>
<HTML>
<BODY bgcolor=cyan>
<Font size=2></Font>
    <CENTER>
        <BR>请输入您的当前的密码和新密码：
        <FORM action="${pageContext.request.contextPath}/HandlePassword" Method="post">
            <BR>当前密码:<Input type=password name="oldPassword">
            <BR>新密码: <Input type=password name="newPassword">
            <BR><Input type=submit name="g" value="提交">
        </Form>
    </CENTER>
</BODY>
</HTML>
