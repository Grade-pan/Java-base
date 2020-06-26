<%--
  Created by IntelliJ IDEA.
  User: 18178
  Date: 2020/4/13
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1 style="color: #0e23ff;text-align: center">文件上传</h1>
<form method="post" action="/JSPTEST2_war_exploded/uploadFile" enctype="multipart/form-data">
    <span>选择一个文件</span>
    <input type="file" name="uploadFile"/>
    <br/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
