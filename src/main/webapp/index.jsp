<%@ page import="org.javalynx.model.User" %>
<%@ page import="org.javalynx.service.UserService" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="utf-16">
    <title>таблица юзеров</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="GET">
        <button>add</button>
    </form>
    <form action="${pageContext.request.contextPath}/update" method="GET">
        <input name="oldfirstname" type="text" placeholder="firstname">
        <input name="oldlastname" type="text" placeholder="lastname">
        <input name="oldpassword" type="password" placeholder="password">
        <button name="ok">edit</button>
    </form>
    <form action="${pageContext.request.contextPath}/delete" method="POST">
        <input name="firstname" type="text" placeholder="firstname">
        <input name="lastname" type="text" placeholder="lastname">
        <input name="password" type="password" placeholder="password">
        <button name="ok">delete</button>
    </form>
    <table border="1">
        <caption>list users in database</caption>
        <%
            UserService userService = UserService.getInstance();
            List<User> list = userService.getAllUsers();
            for(int i = 0; i < list.size(); i++){
                User u = list.get(i);
                out.println("<tr>");
                out.println("<th><form readonly>" + u.getFirstName() +"</form></th>");
                out.println("<th><form readonly>" + u.getLastName() + "</form></th>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>

