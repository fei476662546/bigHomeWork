<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/mkfriend/head.txt" %>
</head>

<BODY bgcolor=cyan>
<center><Font size=3></Font>
showLookedMember.jsp负责显示被查找的会员的信息。
    <table>
        <FORM action="helpShowMember.do" method="post" name="form">
            <BR>分页显示全体会员
            <INPUT type="hidden" value="1" name="currPage" size=${pages}>
            <INPUT type="submit" value="显示" name="submit">
        </Form>
        <FORM action="helpShowMember.do" method="get" name="form">
            <br>输入要查找的会员名：
            <INPUT type="text"  name="currPagelogname" size=${pages}>
            <INPUT type="submit" value="显示" name="submit">
        </FORM>
    </table>
</center>
</BODY>
</HTML>
