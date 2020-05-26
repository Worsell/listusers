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
<form method="post" action="/admin/update">
    <input type="text" name="firstname" placeholder="input firstname">
    <input type="text" name="lastname" placeholder="input lastname">
    <select name="role">
        <option value="admin">admin</option>
        <option selected="selected" value="user">user</option>
    </select>
    <input type="password" name="password" placeholder="password">
    <input type="hidden" name="id" placeholder="password" value=${param.get("id")}>
    <button>ok</button>
</form>
</body>
</html>
