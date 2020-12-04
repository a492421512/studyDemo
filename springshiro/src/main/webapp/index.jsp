<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Document</title>
</head>
<body>

    <h1>首页</h1>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
    <ul>
        <shiro:hasAnyRoles name="admin,user"> <%--只有admin,user才可以访问--%>
            <li><a href="">用户管理</a></li>
                <ul>
                    <%--资源控制--%>
                    <shiro:hasPermission name="user:create:*"><li>增加1</li></shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*"><li>删除1</li></shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*"><li>修改1</li></shiro:hasPermission>
                    <shiro:hasPermission name="order:select:*"><li>查询1</li></shiro:hasPermission>

                    <%--li shiro:hasPermission="user:delete:*">删除</li>
                    <li shiro:hasPermission="user:update:*">修改</li>
                    <li shiro:hasPermission="user:select:*">查询</li>--%>
                </ul>
        </shiro:hasAnyRoles>
        <shiro:hasRole name="admin"> <%--只有admin权限才可以访问--%>
            <li><a href="">商品管理</a></li>
            <li><a href="">订单管理</a></li>
            <li><a href="">物流管理</a></li>
        </shiro:hasRole>
    </ul>

</body>
</html>