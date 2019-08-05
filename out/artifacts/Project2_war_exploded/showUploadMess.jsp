<%--
  Created by IntelliJ IDEA.
  User: 47666
  Date: 2019/7/20
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/mkfriend/head.txt" %>
</head>
<style>

</style>
<body>
<CENTER>
<div ><font color="red">showUploadMess.jsp负责显示上传文件的反馈信息。</font></div>

<img src="/Project2_war_exploded/img/<%=request.getSession().getAttribute("fileName")%>" width="200px">
</CENTER>
</body>
</html>
