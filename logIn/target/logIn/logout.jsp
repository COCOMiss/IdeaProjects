<%@ page import="entity.Reader" %><%--
  Created by IntelliJ IDEA.
  User: COCOMiss
  Date: 2017/5/27
  Time: 下午9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
   Reader reader= (Reader) session.getAttribute("reader");
   String name= reader.getName();
%>
欢迎您，${name}  <br>
<a href="/LogOutServlet">退出登录</a>
</body>
</html>
