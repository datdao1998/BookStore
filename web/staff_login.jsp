<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/1/2019
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 100%; text-align: center; font-size: 100px; padding-top: 60px;">WELCOME TO BOOK STORE</div>
<div style="width: 100%;text-align: center;font-size: 30px">This page for staff only</div>
<div style="margin-left: 42%; margin-top: 10%;">
    <form action="staff-login" method="post">
        <input style="width: 25%; min-height: 30px;" type="text" name="staff-username" placeholder="Tên đăng nhập..."><br><br>
        <input style="width: 25%; min-height: 30px;" type="password" name="staff-password" placeholder="Mật khẩu..."><br><br>
        <button type="submit" style="margin-left: 8%; min-width: 10%; min-height: 3%;">Đăng nhập</button>
    </form>
    <label style="margin-left: 4vh" id="authentication-message">
        <%
            String message = (String) request.getAttribute("staffAuthenticationMessage");
            if(message != null) out.print(message);
        %>
    </label>
</div>
</body>
</html>
