<%@ page import="model.entity.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ABC
  Date: 10/30/2019
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="css.css">
</head>
<body>
    <%
        if((session.getAttribute("customerIsAuthenticated") != "true") ||
            session == null || session.getAttribute("customerIsAuthenticated") == null) response.sendRedirect("index.jsp");
    %>
    <a href="cart_detail.jsp" class="link-cart-detail">Xem giỏ hàng</a>
    <a href="/book-store/logout" class="link-cart-detail">Đăng xuất</a>
    <div style="float: right; padding-right: 5%; font-size: x-large; padding-top: 2%;">Xin chào <%= session.getAttribute("customerName")%></div>
    <div style="clear:both; width: 100%; text-align: center; font-size: 70px; padding-top: 30px;">BOOK STORE</div>
    <form action="search-book">
        <input id="search-book-text" style="margin-bottom: 1%;min-width: 25%;clear: both;float: left;min-height: 4vh;margin-left: 35%;margin-top: 1%;border-radius: 5px;border-color: black;" type="text" placeholder="Tên sách..." name="book-name-search" >
        <button id="btn-search-book" style="min-height: 4vh;float: left;min-width: 5%;margin-left: 0.5%;border-radius: 5px;border-color: darkgrey;margin-top: 1%;" type="submit">Tìm kiếm</button>
    </form>
    <table id="table-books">
        <tr>
            <th class="th-book-name">TÊN SÁCH</th>
            <th class="th-book-author">TÁC GIẢ</th>
            <th class="th-book-category">THỂ LOẠI</th>
            <th class="th-book-publisher">NHÀ XUẤT BẢN</th>
            <th class="th-book-price">GIÁ</th>
            <th class="th-book-available">CÒN LẠI</th>
            <th class="th-book-add-to-cart">THÊM VÀO GIỎ HÀNG</th>
        </tr>
        <%  List<Book> books = (List<Book>) request.getAttribute("listBook");
            if(books != null) {
                for (int i = 0; i<books.size(); i++) { %>
                <tr>
                 <td><%= books.get(i).getName()%></td>
                    <td><%= books.get(i).getAuthor()%></td>
                    <td><%= books.get(i).getCategory()%></td>
                    <td><%= books.get(i).getPublisher()%></td>
                    <td><%= books.get(i).getPrice()%></td>
                    <td><%= books.get(i).getAvailable()%></td>
                    <form id="form-add-book-to-cart" action="add-book-to-cart">
                        <input name="book-name" type="hidden">
                        <input name="book-id" type="hidden" value="<%= books.get(i).getId()%>">
                        <td>
                            <button id="btn-add-book-to-cart-<%= i%>" onclick="submit()">Thêm vào giỏ hàng</button>
                        </td>
                    </form>
                </tr>
        <% } } %>
    </table>

    <script>
        function submit() {
            var bookName = document.getElementById("search-book-text").value;
            document.getElementById("book-name").setAttribute("value", bookName);
            document.getElementById("form-add-book-to-cart").submit();
        }
    </script>
</body>
</html>
