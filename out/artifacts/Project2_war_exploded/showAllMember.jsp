<%@ page import="java.lang.annotation.Target" %><%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>信息展示</title>
    <%@ include file="/mkfriend/head.txt" %>
    <style>
        .t2{ text-align: center;}
        body{ padding: 0;margin: 0;background: url("${pageContext.request.contextPath}/img/2.jpg") no-repeat;
            background-size: 100% 100%;}
        td a{text-decoration: none;color: white;}
    </style>
</head>
<body>
<center>
    <BR>当前显示的内容是：
    <table class="t2" border=2>
        <thead>
        <tr>
            <th>会员名</th>
            <th>电话</th>
            <th>email</th>
            <th>简历和交友标准</th>
            <th>用户照片</th>
        </tr>
        </thead>
        <c:forEach items="${currlist}" var="user" varStatus="status">
            <%--<tr bgcolor="#79e9ff" <c:if test="${status.index%2==1}">style="background-color: rgba(255,245,198,0.93);" </c:if>>--%>
            <tr>
                <td>${user.logname}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>${user.message}</td>
                <td><img src="${pageContext.request.contextPath}/img/${user.pic}" width=150 height=120 /></td>
            </tr>
        </c:forEach>
    </table>
    <BR>每页最多显示<%=request.getAttribute("page_size")%>条信息
    <BR>当前显示第<Font color=#ffc0cb>
    <%=request.getAttribute("currPage")%>
</Font>页,共有
    <Font color="#ffc0cb"><%=request.getAttribute("pages")%>
    </Font>页。
    <BR>单击“上一页”或“下一页”按纽查看信息
    <Table>
        <tr>
            <td>
                <FORM action="${pageContext.request.contextPath}/helpShowMember.do" method=post>
                    <Input type=hidden name="currPage" value="${currPage-1}">
                    <Input type=submit name="g" value="上一页">
                </FORM>
            </td>
            <td>
                <FORM action="${pageContext.request.contextPath}/helpShowMember.do" method=post>
                    <Input type=hidden name="currPage" value="${currPage+1}">
                    <Input type=submit name="g" value="下一页">
                </Form>
            </td>
            <td>
                <FORM action="${pageContext.request.contextPath}/helpShowMember.do" method=post>
                    输入页码：<Input type=text name="currPage" size=${pages}>
                    <Input type=submit name="g" value="提交">
                </FORM>
            </td>
        </tr>
    </Table>
</Center>

</body>
</html>