package controller.servlet;

import controller.service.AccountCustomerService;
import controller.service.CustomerService;
import controller.service.impl.AccountCustomerServiceImpl;
import controller.service.impl.CustomerServiceImpl;
import dto.CreateAccountCustomerReq;
import model.dao.AccountCustomerDAO;
import model.dao.CustomerDAO;
import model.dao.impl.AccountCustomerDAOImpl;
import model.dao.impl.CustomerDAOImpl;
import model.entity.AccountCustomer;
import model.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerSignUpServlet extends HttpServlet {

    private AccountCustomerService accountCustomerService = new AccountCustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CreateAccountCustomerReq req = new CreateAccountCustomerReq();
        req.setAddress(request.getParameter("address-sign-up"));
        req.setIdCard(request.getParameter("id-card-sign-up"));
        req.setName(request.getParameter("name-sign-up"));
        req.setPassword(request.getParameter("password-sign-up"));
        req.setPhone(request.getParameter("phone-sign-up"));
        req.setUserName(request.getParameter("user-name-sign-up"));

        boolean isExecuted = accountCustomerService.create(req);
        if(isExecuted) response.sendRedirect("sign_up_success.jsp");
        else response.sendRedirect("sign_up_fail.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
