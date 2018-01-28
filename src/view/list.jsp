<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 2017-12-10
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入shiro标签-->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>List Page</title>
</head>
<body>
<h1>List Page</h1>
<br><br>
<shiro:hasRole name="admin">
<<a href="/view/admin.jsp">Admin Page</a>
</shiro:hasRole>
<br><br>
<shiro:hasRole name="user">
<<a href="/view/user.jsp">User Page</a>
</shiro:hasRole>
<br><br>
<<a href="/shiro/logout">LogOut</a>


<br><br>
<<a href="/shiro/testAnnontation">TestAnnontation</a>
</body>
</html>
