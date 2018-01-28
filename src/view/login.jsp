<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 2017-12-10
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login P</title>
</head>
<body>
<form action="/shiro/login" method="POST">
    userName:<input type="text" name="username"/>
    <br><br/>
    passWord:<input type="password" name="password"/>
    <input type="submit" value="Submit"/>
</form>




</body>
</html>
