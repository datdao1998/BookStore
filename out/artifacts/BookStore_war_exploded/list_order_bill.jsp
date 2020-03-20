<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.OrderBill" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/5/2019
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    Danh sách đơn hàng
</div>
<div style="text-align: center;font-size: 70px; width: 100%;float: left;table-layout: auto">
    <table style="width: 80%;margin-left: 10%">
            <tr>
                <th>
                    Mã đơn hàng
                </th>
                <th>
                    Mã khách hàng
                </th>
                <th>
                    Trạng thái
                </th>
                <th>
                    Mã nhân viên
                </th>
                <th>
                    Tên nhân viên
                </th>
                <th>
                    Chi tiết
                </th>
            </tr>
        <%
            ArrayList<OrderBill> list = (ArrayList<OrderBill>) session.getAttribute("listOrderBill");
            for (int i = 0 ; i<list.size() ; i++){
        %>
         <tr>
             <th>
                 <%=list.get(i).getId()%>
             </th>
             <th>
                 <%=list.get(i).getCustomer().getId()%>
             </th>
             <th>
                 <%=list.get(i).getStatus()%>
             </th>
             <th>
                 <%=list.get(i).getStaffCode()%>
             </th>
             <th>
                 <%=list.get(i).getStaffName()%>
             </th>
             <th>
                 <form action="order-bill-detail">
                     <input type="hidden" value="<%=list.get(i).getId()%>" name="orderBillId">
                     <input type="submit" value="Xem chi tiết">
                 </form>
             </th>
         </tr>
        <%
            }
        %>
    </table>

</div>
<div style="width: 100%;text-align: center;font-size: 30px;height:50px;float: left">

    <button id="cancel" onclick="cancel()">Quay lại</button>

</div>
<script>
    function cancel() {
        location.href = "staff_home.jsp";
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
        var length = <%=list.size()%>;
        xhttp.open("POST", "check-new-orderBill-on-billList", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("length="+length);
    },3000)

</script>
</body>

</html>
