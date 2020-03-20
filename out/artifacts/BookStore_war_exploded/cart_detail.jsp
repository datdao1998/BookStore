<%@ page import="model.entity.OrderedItem" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ABC
  Date: 10/31/2019
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giỏ hàng</title>
    <link rel="stylesheet" type="text/css" href="css.css">
</head>
<body>
    <%
        if((session.getAttribute("customerIsAuthenticated") != "true") ||
                session == null || session.getAttribute("customerIsAuthenticated") == null) response.sendRedirect("index.jsp");
    %>
    <a class="link-search-book" href="/book-store/search-book">Xem danh sách sản phẩm</a>
    <a href="/book-store/logout" class="link-cart-detail">Đăng xuất</a>
    <div style="float: right; padding-right: 5%; font-size: x-large; padding-top: 2%;">Xin chào <%= session.getAttribute("customerName")%></div>
    <div style="clear:both; width: 100%; text-align: center; font-size: 70px; padding-top: 30px;">THÔNG TIN GIỎ HÀNG</div>
    <table>
        <tr>
            <th class="th-book-name">TÊN SÁCH</th>
            <th class="th-book-author">TÁC GIẢ</th>
            <th class="th-book-category">THỂ LOẠI</th>
            <th class="th-book-publisher">NHÀ XUẤT BẢN</th>
            <th class="th-book-price">GIÁ</th>
            <th class="th-book-available">CÒN LẠI</th>
            <th class="th-book-quantity">SỐ LƯỢNG</th>
        </tr>
        <%  List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
            if(cart != null) {
                for (int i = 0; i<cart.size(); i++) { %>
        <tr>
            <td><%= cart.get(i).getBook().getName()%></td>
            <td><%= cart.get(i).getBook().getAuthor()%></td>
            <td><%= cart.get(i).getBook().getCategory()%></td>
            <td><%= cart.get(i).getBook().getPublisher()%></td>
            <td><%= cart.get(i).getBook().getPrice()%></td>
            <td><%= cart.get(i).getBook().getAvailable()%></td>
            <td>
                <form action="increase-quantity" style="float: left; padding-left: 29%; padding-top: 2%; margin-bottom: 2%;">
                    <input type="hidden" name="index" value="<%= i %>">
                    <button type="submit">+</button>
                </form>
                <div style="float: left; padding-left: 0.5vw; padding-top: 2%; margin-bottom: 2%;">
                        <%= cart.get(i).getQuantity()%>
                </div>
                <form action="decrease-quantity" style="float: left; padding-left: 0.5vw; padding-top: 2%; margin-bottom: 2%;">
                    <input type="hidden" name="index" value="<%= i %>">
                    <button type="submit">-</button>
                </form>
            </td>
        </tr>
        <% } }%>
    </table>
    <a class="link-create-order" href="/book-store/forward-create-order-request">Tạo đơn hàng</a>
</body>
</html>
