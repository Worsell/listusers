<html>
<head>
    <meta charset="utf-16">
    <title>user console</title>
</head>
<body>
<table>
    <caption>information about your users</caption>
    <%
            String firstname = (String) session.getAttribute("firstname");
            String lastname = (String) session.getAttribute("lastname");
            out.println("<tr>");
            out.println("<th><form readonly>" + firstname +"</form></th>");
            out.println("<th><form readonly>" + lastname + "</form></th>");
            out.println("</tr>");
        %>
</table>
</body>
</html>
