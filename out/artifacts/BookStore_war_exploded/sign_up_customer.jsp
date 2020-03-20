<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
    <div style="width: 100%; text-align: center; font-size: 70px; padding-top: 60px;">Đăng ký</div>
    <div style="margin-left: 42%; margin-top: 10%;">
        <form id="sign-up-form" method="post" action="customer-sign-up">
            <input id="user-name-sign-up" style="width: 25%; min-height: 30px;" type="text" name="user-name-sign-up" placeholder="Tên đăng nhập..." onchange="checkUserNameExisted()">
            <label id="check-user-name"></label><br><br>
            <input id="password-sign-up" style="width: 25%; min-height: 30px;" type="password" name="password-sign-up" placeholder="Mật khẩu..."><br><br>
            <input id="repeat-password-sign-up" style="width: 25%; min-height: 30px;" type="password" name="repeat-password-sign-up" placeholder="Nhập lại mật khẩu..."><br><br>
            <input id="id-card-sign-up" style="width: 25%; min-height: 30px;" type="text" name="id-card-sign-up" placeholder="CMND..." onchange="checkCustomerExisted()">
            <label id="check-customer-existed"></label><br><br>
            <input id="name-sign-up" style="width: 25%; min-height: 30px;" type="text" name="name-sign-up" placeholder="Họ và tên..."><br><br>
            <input id="address-sign-up" style="width: 25%; min-height: 30px;" type="text" name="address-sign-up" placeholder="Địa chỉ..."><br><br>
            <input id="phone-sign-up" style="width: 25%; min-height: 30px;" type="text" name="phone-sign-up" placeholder="Số điện thoại..."><br><br>
            <input id="btn-sign-up" type="button" style="margin-left: 8%; min-width: 10%; min-height: 3%;" value="Đăng ký" onclick="register()">
        </form>
        <a href="index.jsp" style="margin-left: 10%;">Đăng nhập</a>
    </div>
    <script>
        function checkCustomerExisted() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var data = JSON.parse(this.response);
                    if(data.id != null) {
                        document.getElementById("check-customer-existed").innerHTML = "Thông tin của bạn đã có sẵn.";
                        document.getElementById("name-sign-up").value = data.name;
                        document.getElementById("name-sign-up").setAttribute("readonly", "true");
                        document.getElementById("address-sign-up").value = data.address;
                        document.getElementById("address-sign-up").setAttribute("readonly", "true");
                        document.getElementById("phone-sign-up").value = data.phone;
                        document.getElementById("phone-sign-up").setAttribute("readonly", "true");
                    }
                    else {
                        document.getElementById("check-customer-existed").innerHTML = "";
                        document.getElementById("name-sign-up").value = "";
                        document.getElementById("address-sign-up").value = "";
                        document.getElementById("phone-sign-up").value = "";
                        document.getElementById("name-sign-up").removeAttribute("readonly");
                        document.getElementById("address-sign-up").removeAttribute("readonly");
                        document.getElementById("phone-sign-up").removeAttribute("readonly");
                    }
                }
            };
            var idCard = document.getElementById("id-card-sign-up").value;
            xhttp.open("POST", "check-customer-existed", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("id-card="+idCard);
        }
        function checkUserNameExisted() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("check-user-name").innerHTML = this.responseText;
                }
            };
            var userName = document.getElementById("user-name-sign-up").value;
            xhttp.open("POST", "account-customer-check-user-name-existed", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("user-name="+userName);
        }
        function register() {
            var password = document.getElementById("password-sign-up").value;
            var rePassword = document.getElementById("repeat-password-sign-up").value;
            var userName = document.getElementById("user-name-sign-up").value;
            var name = document.getElementById("name-sign-up").value;
            var idCard = document.getElementById("id-card-sign-up").value;
            var address = document.getElementById("address-sign-up").value;
            var phone = document.getElementById("phone-sign-up").value;

            if(name == null || password == null || rePassword == null || userName == null || idCard == null || address == null || phone == null) {
                alert("Không được để trống thông tin đăng nhập.");
                return;
            }

            if (!(password === rePassword)) {
                alert("2 mật khẩu bạn nhập không trùng nhau");
                return;
            }

            if(document.getElementById("check-user-name").innerHTML == "User name is existed. Please choose another user name!") {
                alert("Tên đăng nhập đã tồn tại.");
                return;
            }
            document.getElementById("sign-up-form").submit();
        }
    </script>
</body>
</html>
