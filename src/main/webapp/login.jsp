<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login in application</title>
</head>
<body>
    <form action="/login" method="POST">
        <input type="text" name="firstname" placeholder="input firstname">
        <input type="text" name="lastname" placeholder="input lastname">
        <input type="password" name="password" placeholder="password">
        <button>press to login</button>
    </form>
</body>
</html>
