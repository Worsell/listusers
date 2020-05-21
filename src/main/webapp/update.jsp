<%--
  Created by IntelliJ IDEA.
  User: ivan
  Date: 24/04/2020
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/update">
    <input type="text" name="newfirstname" placeholder="input firstname">
    <input type="text" name="newlastname" placeholder="input lastname">
    <input type="password" name="newpassword" placeholder="password">
    <button>ok</button>
</form>
</body>
</html>
