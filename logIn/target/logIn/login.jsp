<%@ page import="Dao.BookDao" %>
<%@ page import="entity.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: COCOMiss
  Date: 2017/5/27
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="LoginServlet" method="post">
    用户id：<input type="text" name="reader_name">
    登录密码：<input type="password" name="reader_password">
    <input type="submit" value="submit">
</form>
<a href="regist.jsp"><font size="2"><i>没有帐号？点击注册</i></font></a>
<a href="changePassword.jsp"><font size="2"><i>修改密码</i></font></a>
<table border="1">
    <tr>
        <td>author</td>
        <td>name</td>
        <td>borrowed</td>
        <td>发行时间</td>
    </tr>
    <%
        BookDao dao=new BookDao();
        List<Book> list =dao.getBookByName("活着");
        for(Book book:list)
        {%>
    <tr>
        <td><%=book.getAuthor() %></td>
        <td><%=book.getName() %></td>
        <td><%=book.getBorrowed()%>></td>
        <td><%=book.getPub_date()%></td>
    </tr>
    <%}
    %>
</table>
</body>
</html>
