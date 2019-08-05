<%@ page import="mybean.data.javabean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>

<HEAD>
    <%@ include file="/mkfriend/head.txt" %>
</HEAD>

<HTML>
<BODY bgcolor=pink>
<%--<%--%>
<%--    List<User> list = (List<User>) request.getSession().getAttribute("list");--%>
<%--    if (list == null || list.size() < 1) {--%>
<%--        out.print("没有用户信息");--%>
<%--    } else {--%>
<%--        for (User user : list) {--%>
<%--%>--%>
<Center>
    <table border=2>
        <tr>
            <th>会员名</th>
            <th>电话</th>
            <th>email</th>
            <th>简历和交友标准</th>
            <th>用户照片</th>
        </tr>
    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.logname}
            </td>
            <td>${user.phone}
            </td>
            <td>${user.email}
            </td>
            <td>${user.message}
            </td>
            <td><img src="/Project2_war_exploded/img/${user.pic}" width="200px">
            </td>
        </tr>
</c:forEach>
    </table>
<%--    <%}--%>
<%--}--%>
<%--   --%>
<%--    %>--%>
</Center>
</BODY>
</HTML>
