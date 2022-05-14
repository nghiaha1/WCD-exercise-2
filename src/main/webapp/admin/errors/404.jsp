<%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 5/12/22
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String message = (String) request.getAttribute("message");
%>
<html>
<head>
    <title><%=message%></title>
</head>
<body>
  <h1><%=message%></h1>
  <img src="https://webdeasy.de/wp-content/uploads/2020/06/404-pages.jpg" alt="<%=message%>">
</body>
</html>
