<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/6
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>睿乐购后台登录</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/base.css">
</head>
<body>
<div id="wk">
    <div id="container">
        <form action="manage/user/login.do" method="post" id="con-form">
            <input type="text" name="username" placeholder="输入账户" class="cf-f">
            <input type="password" name="password" placeholder="输入密码" class="cf-f">
            <input type="submit" value="登录" id="cf-s">
        </form>
    </div>
</div>
</body>
</html>
