package controller.servlet;

import controller.service.AccountCustomerService;
import controller.service.impl.AccountCustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckUserNameExistedServlet extends HttpServlet {

    private AccountCustomerService accountCustomerService = new AccountCustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user-name");
        String res = accountCustomerService.checkUserNameExisted(userName);
        response.getWriter().print(res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
