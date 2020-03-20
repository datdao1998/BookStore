<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.OrderBill" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/31/2019
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        button{
            width: 4cm;
            height: 2cm;
            text-align: center;
            font-style: oblique ;
            font-size: 20px;

        }
    </style>
</head>
<body>
<%
    if((session.getAttribute("staffIsAuthenticated") != "true") ||
            session == null || session.getAttribute("staffIsAuthenticated") == null) response.sendRedirect("staff_login.jsp");
%>
<div style="text-align: end;font-size: 25px; width: 100%; height: 100px;float: left">
    <h3>
        Xin chào : <%
        out.print(session.getAttribute("staffName"));
    %>
    </h3>
</div>
<div style="width: 100%; text-align: center; font-size: 100px; padding-top: 60px;">Staff Home</div>
<div style="text-align: right; height: 50px;font-size: 50px">
    <a href="order_handle.jsp" style="display: none">Chi tiết đơn hàng</a>
</div>
<div style="width: 100%;text-align: center;height: 500px">
    <form action="order-handle">
        <button>Xử lý đơn hàng</button>
    </form>
    <form action="follow-add-book">
        <button>Thêm sách</button>
    </form>
    <form action="creat-order-bill">
        <button>Hóa Đơn</button>
    </form>
</div>


</body>
</html>
