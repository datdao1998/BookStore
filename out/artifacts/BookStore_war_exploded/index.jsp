<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Book Store</title>
  </head>
  <body>
    <div style="width: 100%; text-align: center; font-size: 100px; padding-top: 60px;">WELCOME TO BOOK STORE</div>
    <div style="margin-left: 42%; margin-top: 10%;">
      <form action="customer-login" method="post">
        <input style="width: 25%; min-height: 30px;" type="text" name="user-name-login" placeholder="Tên đăng nhập..."><br><br>
        <input style="width: 25%; min-height: 30px;" type="password" name="password-login" placeholder="Mật khẩu..."><br><br>
        <button type="submit" style="margin-left: 8%; min-width: 10%; min-height: 3%;">Đăng nhập</button>
      </form>
      <label style="margin-left: 4vh" id="authentication-message">
        <%
          String message = (String) request.getAttribute("customerAuthenticationMessage");
          if(message != null) out.print(message);
        %>
      </label><br><br>
      <a href="sign_up_customer.jsp" style="margin-left: 10%;">Đăng ký</a><br><br>
    </div>
  </body>
</html>
