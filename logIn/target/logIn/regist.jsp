<%--
  Created by IntelliJ IDEA.
  User: COCOMiss
  Date: 2017/5/27
  Time: 下午5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注册</h1>
<form action="RegistServlet" method="post">
    请输入姓名：<input type="text" name="name"><br/>
    <select id="style" name="style">
        <option value="0">student</option>
        <option value="1">teacher</option>
        <option value="3">administer</option>
    </select>
    请输入密码：<input type="password" name="password"><br/>
    请确认密码：<input type="password" name="rpsw"><br/>
    <input type="submit" value="注册">
</form>

</body>
</html>
