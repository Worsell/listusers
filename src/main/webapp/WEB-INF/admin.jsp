<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-16">
    <title>admin console</title>
</head>
<body>
<form action="/admin/create" method="POST">
    <button>add user/admin</button>
    <input name="firstname" type="text" placeholder="firstname">
    <input name="lastname" type="text" placeholder="lastname">
    <input name="password" type="password" placeholder="password">
    <select name="role">
        <option value="admin">admin</option>
        <option selected="selected" value="user">user</option>
    </select>
</form>
<table border="1">
    <caption>list users in database</caption>
    <tr>
        <th> id</th>
        <th> FirstName</th>
        <th> LastName</th>
        <th> HashPassword</th>
        <th> Role</th>
        <th> Update</th>
        <th> Delete</th>
    </tr>
    <c:forEach var="user" items="${usersFromServlet}">
            <tr>
                <th>${user.id}</th>
                <th>${user.firstName}</th>
                <th>${user.lastName}</th>
                <th>${user.password}</th>
                <th>${user.role}</th>
                <th>
                    <form action="/admin/update" method="GET">
                        <button name="ok">press</button>
                        <input name="id" type="hidden" placeholder="firstname" value=${user.id}>
                    </form>
                </th>
                <th>
                    <form action="/admin/delete" method="POST">
                        <button name="ok">press</button>
                        <input name="id" type="hidden" placeholder="firstname" value=${user.id}>
                    </form>
                </th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

