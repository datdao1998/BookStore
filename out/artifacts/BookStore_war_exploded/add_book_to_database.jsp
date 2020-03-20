<%@ page import="model.entity.OrderBill" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/5/2019
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        button{
            width: 2cm;
            height: 1cm;
            font-size: 15px;
        }
    </style>
</head>
<body>
<div style="text-align: left;font-size: 20px; width: 50%; height: 100px;float: left">
    <a href="staff_home.jsp" style="display: none" id="newOrder">Có đơn hàng mới</a>
</div>
<div style="text-align: end;font-size: 25px; width: 50%; height: 100px;float: left">
    <h3>
        Xin chào : <%
        out.print(session.getAttribute("staffName"));
    %>
    </h3>
</div>
<div style="text-align: center;font-size: 70px; width: 100%; height: 100px;float: left">
    Thêm Sách
</div>
<div style="text-align: center;font-size: 20px; width: 100%;float: left">
    <form action="add-book-to-database" method="post" id="add-book-form">
        <input type="text" placeholder="Tên" name="name" id="name">
        <br>
        <br>
        <input type="text" placeholder="Tác giả" name="author" id="author">
        <br>
        <br>
        <input type="text" placeholder="Thể loại" name="category" id="category">
        <br>
        <br>
        <input type="text" placeholder="Nhà xuất bản" name="publisher" id="publisher">
        <br>
        <br>
        <input type="text" placeholder="Giá" name="price" id="price">
        <br>
        <br>
        <input type="text" placeholder="Số lượng" name="quantity" id="quantity">
        <br>
        <br>
</form>
    <button onclick="check()">Thêm</button>
</div>
<br>
<div style="width: 100%; height: 50px; text-align: center">
    <button onclick="quit()">Quay lại</button>
</div>
<%
    ArrayList<OrderBill> list2 = (ArrayList<OrderBill>) session.getAttribute("listOrderBill");
%>
<script>
    function check() {
        function isInt(n){
           if(!isNaN(n)){
               return true
           }
           return false
        }

        function isFloat(n){
            if (!isNaN(n) && n.toString().indexOf('.') != -1){
                return true
            }
            return false
        }
        var x = document.getElementById("name").value;
        var y = document.getElementById("author").value;
        var z = document.getElementById("price").value;
        var t = document.getElementById("quantity").value;
        if(x==""||y==""||typeof (x) == undefined||typeof (y) == undefined||x==null||y==null){
            alert("Tên và tác giả là bắt buộc")
        }
        else if(z==""||t==""||typeof (z) == undefined||typeof (t) == undefined||z==null||t==null){
            alert("Giá và số lượng là bắt buộc")
        }
        else{
            if(!isFloat(z)&&!isInt(z)){
                alert("giá phải là kiểu số");
            }
            else{
                if (!isInt(t)){
                    alert("số lượng phải là kiểu số")
                }
                else{
                    document.getElementById("add-book-form").submit();
                }
            }
        }

    }
    function quit() {
        location.href="staff_home.jsp";
    }
    setInterval(function () {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText=="true"){
                    document.getElementById("newOrder").setAttribute("style","display : inline")
                };
            }
        };
        var length = <%=list2.size()%>;
        xhttp.open("POST", "check-new-orderBill-on-billList", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("length="+length);
    },3000)




</script>


</body>
</html>
