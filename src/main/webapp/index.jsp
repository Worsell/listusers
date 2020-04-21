<%@ page import="org.javalynx.controller.service.CarService" %>
<%@ page import="org.javalynx.model.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="utf-16">
    <title>таблица юзеров</title>
</head>
<body>
    <table border="1">
        <caption>list users in database</caption>
        <%
            CarService carService = CarService.getInstance();
            List<User> list = carService.getAllUsers();
            for(int i = 0; i < list.size(); i++){
                User u = list.get(i);
                out.println("<tr>");
                out.println("<th>" + u.getFirstName() +"</th>");
                out.println("<th>"+ u.getLastName() +"</th>");
                out.println("<th><form>\n" +
                        "  <button>add</button>\n" +
                        "</form> </th>");
                out.println("<th><form>\n" +
                        "  <button>remove</button>\n" +
                        "</form> </th>");
                out.println("<th><form>\n" +
                        "  <button>edit</button>\n" +
                        "</form> </th>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>

