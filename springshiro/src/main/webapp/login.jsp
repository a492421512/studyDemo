<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Document</title>
</head>
<body>

    <h1>用户登录</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名：<input type="text" name="username"> <br/>
        密码：<input type="password" name="password"> <br/>
        <input type="submit" value="登录">
    </form>
</body>
</html>