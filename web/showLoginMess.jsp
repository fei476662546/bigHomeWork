<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="mybean.data.javabean.User" %><%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/17
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<HEAD>
    <%@ include file="/mkfriend/head.txt" %>
</HEAD>
<HTML>
<BODY bgcolor=pink>

<h1>欢迎登录！${pageContext.servletContext.getAttribute("logname")}</h1>

    用户名：${pageContext.servletContext.getAttribute("logname")}<br>
    密码：${pageContext.servletContext.getAttribute("pwd")}


</BODY>
</HTML>