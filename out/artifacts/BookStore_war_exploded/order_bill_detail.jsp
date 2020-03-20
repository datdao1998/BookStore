<%@ page import="model.entity.OrderedItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="model.entity.OrderBill" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/5/2019
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            clear: both;
            width: 80%;
            margin-left: 10%;
            padding-top: 2%;
            border-collapse: collapse;
            border: black 1px solid;
        }
        th {
            border-collapse: collapse;
            border: black 1px solid;
        }

        td {
            text-align: center;
            border-collapse: collapse;
            border: black 1px solid;
            min-height: 3vh;
        }
        button{
            width: 2cm;
            height: 1cm;
            font-size: 15px;
        }

    </style>
</head>
<body>
    <div style="width: 100%;text-align: center;font-size: 100px;height: 200px;float: left">
        CHI TIẾT ĐƠN HÀNG
    </div>
    <div style="text-align: center;font-size: 20px; width: 100%; height: 100px;float: left">
        <a href="staff_home.jsp" style="display: none" id="newOrder">Có đơn hàng mới</a>
    </div>
        <%
            ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) session.getAttribute("list_ordered_searched_by_orderId");
            OrderBill orderBill = (OrderBill) session.getAttribute("Order_bill_to_detail");
        %>
    <div style="width: 50%;text-align: center;font-size: 20px;height: 100px;float: left">
        Mã khách hàng : <%= orderBill.getCustomer().getId()%>
        <br>
        Tên khách hàng : <%= orderBill.getCustomer().getName()%>
        <br>
        Mã đơn hàng : <%= orderBill.getId()%>
    </div>
    <div style="width: 50%;text-align: center;font-size: 20px;height: 100px;float: left">
        Mã nhân viên : <%= orderBill.getStaffCode()%>
        <br>
        Tên nhân viên : <%= orderBill.getStaffName()%>
    </div>
    <div style="width: 100%;text-align: center;font-size: 30px;float: left">
        <table>
            <tr>
                <th>
                    Mã Sách
                </th>
                <th>
                    Tên sách
                </th>
                <th>
                    Tác giả
                </th>
                <th>
                    Thể loại
                </th>
                <th>
                    Giá
                </th>
                <th>
                    Số lượng
                </th>
            </tr>
            <%
                for(int i = 0 ; i < list.size() ; i++){
            %>
            <tr>
                <th>
                    <%= list.get(i).getBook().getId()%>
                </th>
                <th>
                    <%= list.get(i).getBook().getName()%>
                </th>
                <th>
                    <%= list.get(i).getBook().getAuthor()%>
                </th>
                <th>
                    <%= list.get(i).getBook().getCategory()%>
                </th>
                <th>
                    <%= list.get(i).getBook().getPrice()%>
                </th>
                <th>
                    <%= list.get(i).getQuantity()%>
                </th>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div style="width: 100%;font-size: 20px;height: 30px;float: left;padding-left: 10%">
        Trạng thái đơn hàng : <%= orderBill.getStatus()%>
    </div>
    <div style="width: 50%;text-align: center;font-size: 30px;height:50px;float: left">

        <button id="cancel" onclick="cancel()">Hủy</button>

    </div>
    <div style="width: 50%;text-align: center;font-size: 30px;height:50px;float: left">
       <button id="printBill" onclick="print()">In hóa đơn</button>
    </div>
    <%
        ArrayList<OrderBill> list2 = (ArrayList<OrderBill>) session.getAttribute("listOrderBill");
    %>
<script>
    function cancel() {
        location.href = "list_order_bill.jsp";
    }
    function print() {
        location.href = "order_update_success.jsp";
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
