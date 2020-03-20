<%@ page import="model.entity.OrderedItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tạo đơn hàng</title>
    <link rel="stylesheet" type="text/css" href="css.css">
</head>
<body>
    <%
        if((session.getAttribute("customerIsAuthenticated") != "true") || session == null || session.getAttribute("customerIsAuthenticated") == null) response.sendRedirect("index.jsp");
    %>
    <a href="cart_detail.jsp" class="link-cart-detail">Xem giỏ hàng</a>
    <a class="link-search-book" href="/book-store/search-book">Xem danh sách sản phẩm</a>
    <div style="float: right; padding-right: 5%; font-size: x-large; padding-top: 2%;">Xin chào <%= session.getAttribute("customerName")%></div>
    <div style="clear:both; width: 100%; text-align: center; font-size: 70px; padding-top: 30px;">THÔNG TIN ĐẶT HÀNG</div>
    <div style="padding-top: 4vh; padding-bottom: 2vh; margin-left: 10%;">
        <label class="label-create-order">Tên khách hàng: </label>
        <label class="label-create-order"><% out.print(session.getAttribute("customerName"));%></label><br><br>
        <label class="label-create-order">Địa chỉ: </label>
        <label class="label-create-order"><% out.print(session.getAttribute("customerAddress"));%></label><br><br>
        <label class="label-create-order">Số điện thoại: </label>
        <label class="label-create-order"><% out.print(session.getAttribute("customerPhone"));%></label><br><br>
    </div>
    <div>
        <table>
            <tr>
                <th class="th-book-name">TÊN SÁCH</th>
                <th class="th-book-author">TÁC GIẢ</th>
                <th class="th-book-category">THỂ LOẠI</th>
                <th class="th-book-publisher">NHÀ XUẤT BẢN</th>
                <th class="th-book-price">GIÁ</th>
                <th class="th-book-quantity">SỐ LƯỢNG</th>
                <th class="th-money">THÀNH TIỀN</th>
            </tr>
            <%  List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
                Double total = Double.valueOf("0");
                if (cart != null) {
                    for (int i = 0; i < cart.size(); i++) { %>
            <tr>
                <td><%= cart.get(i).getBook().getName()%></td>
                <td><%= cart.get(i).getBook().getAuthor()%></td>
                <td><%= cart.get(i).getBook().getCategory()%></td>
                <td><%= cart.get(i).getBook().getPublisher()%></td>
                <td><%= cart.get(i).getBook().getPrice()%></td>
                <td><%= cart.get(i).getQuantity()%></td>
                <td><%= (Double) cart.get(i).getBook().getPrice()*cart.get(i).getQuantity()%></td>
            </tr>
            <%  total += cart.get(i).getBook().getPrice()*cart.get(i).getQuantity();} }%>
        </table>
        <% if(total > 0) { %>
            <div style="float: right; font-size: 20px; margin-right: 10%;">Tổng thanh toán: <%= total%></div>
        <% } %>
        <a href="/book-store/create-order" class="link-payment">Thanh toán</a>
    </div>

</body>
</html>
