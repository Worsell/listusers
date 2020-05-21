<%--
  Created by IntelliJ IDEA.
  User: ivan
  Date: 24/04/2020
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/create">
        <input type="text" name="firstname" placeholder="input firstname">
        <input type="text" name="lastname" placeholder="input lastname">
        <input type="password" name="password" placeholder="password">
        <button>ok</button>
    </form>
</body>
</html>
