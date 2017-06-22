<%@ page import="entity.Reader" %><%--
  Created by IntelliJ IDEA.
  User: COCOMiss
  Date: 2017/5/27
  Time: 下午9:51
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
%>

<h1>Wellcome,<%=reader.getName()%></h1>
<a href="/LogoutServlet">退出登录</a>
</body>
</html>
