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
<body bgcolor=yellow>
负责上传文件的表单

<Font size=2 color=blue>
    <CENTER>
        <BR>文件将被上传到 Web服务目录mkfrend的子目录image中。
        <BR>选择要上传的图像照片文件(名字不可以含有非ASCII码字符，比如汉字等)：
        <FORM action="${pageContext.request.contextPath}/HandleUpload.do" method="post" enctype="multipart/form-data">
            <INPUT type=file name="file" size="40">
            <BR><INPUT type="submit" name ="g" value="提交">
        </FORM>
    </CENTER>
</Font>
</body>
</html>
