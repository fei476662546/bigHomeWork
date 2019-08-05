<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>


<HEAD><%@ include file="/mkfriend/head.txt" %></HEAD>
<HTML><BODY bgcolor=yellow >
<Font size=3></Font>
    <CENTER>
        您修改信息如下：
        <form action="${pageContext.request.servletContext}/ModifyPassword.do" method="post">
        <table border=1>
            <tr>
                <td>新电话</td>
                <td>新email</td>
                <td>新简历和交友标准</td>
            </tr>
            <tr>
                <td>${pageContext.request.session.getAttribute("phone")}</td>
                <td>${pageContext.request.session.getAttribute("email")}</td>
                <td><textarea>${pageContext.request.session.getAttribute("message")} </textarea>
                </td>
            </tr>
        </table>
        </form>
</CENTER>
</BODY>
</HTML>