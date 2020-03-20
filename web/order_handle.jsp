<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/4/2019
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="model.dao.OrderedItemDAO"

%>
<%@ page import="model.dao.impl.OrderedItemDAOImpl" %>
<%@ page import="model.entity.OrderedItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.OrderBill" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            text-align: center;
        }

        th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
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
    Xử Lý Đơn Hàng
</div>
<div style="text-align: center;font-size: 70px; width: 100%;float: left;table-layout: auto">
    <table style="width: 80%;margin-left: 10%">
        <tr>
            <th>
               Mã đơn hàng
            </th>
            <th>
                Tên Mặt Hàng
            </th>
            <th>
                Số Lượng
            </th>
            <th>
                Trạng Thái
            </th>
            <th>
                Chi tiết
            </th>
        </tr>
        <%
            ArrayList<OrderBill> orderBills = (ArrayList<OrderBill>) session.getAttribute("listOrderBill");
            OrderedItemDAO orderedItemDAO = new OrderedItemDAOImpl();
            for(int i = 0 ; i < orderBills.size() ; i++){
                ArrayList<OrderedItem> orderedItems = (ArrayList<OrderedItem>) orderedItemDAO.getAllByOrderId(orderBills.get(i).getId());
                int size = orderedItems.size();
        %>
            <tr>
                <th rowspan="<%=size%>">
                    <%= orderBills.get(i).getId()%>
                </th>
                <th>
                    <%= orderedItems.get(0).getBook().getName()%>
                </th>

                <th>
                    <%= orderedItems.get(0).getQuantity()%>
                </th>
                <th rowspan="<%= size%>">
                    <%= orderBills.get(i).getStatus()%>
                </th>
                <th rowspan="<%=size%>">
                    <form action="order-detail" >
                        <input type="submit" value="Xem chi tiết">
                        <input type="hidden" value="<%=orderBills.get(i).getId()%>" name="orderId">
                    </form>
                </th>
            </tr>
            <%
                for(int j = 1 ; j < orderedItems.size() ; j++){

            %>
            <tr>
                <th>
                    <%= orderedItems.get(j).getBook().getName()%>
                </th>
                <th>
                    <%= orderedItems.get(j).getQuantity()%>
                </th>
            </tr>
        <%
            }
        %>
        <%
            }
        %>
    </table>

</div>
<script>
    setInterval(function () {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText=="true"){
                    document.getElementById("newOrder").setAttribute("style","display : inline")
                };
            }
        };
        var length = <%=orderBills.size()%>;
        xhttp.open("POST", "check-new-orderBill", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("length="+length);
    },3000)

</script>
<div style="width: 100%;height: 80px;font-size: 20px;text-align: center">
    <button onclick="location.href='staff_home.jsp'">Quay lại</button>
</div>


</body>
</html>
