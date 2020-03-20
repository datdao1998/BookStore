package controller.servlet;

import controller.service.AccountCustomerService;
import controller.service.impl.AccountCustomerServiceImpl;
import model.entity.AccountCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerLoginServlet extends HttpServlet {

    AccountCustomerService accountCustomerService = new AccountCustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user-name-login");
        String password = request.getParameter("password-login");
        HttpSession session = request.getSession(true);
        AccountCustomer accountCustomer = accountCustomerService.authentication(userName, password);
        if (accountCustomer.getId() == null) {
            session.setAttribute("customerIsAuthenticated", "false");
            request.setAttribute("customerAuthenticationMessage", "User name or password is invalid");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
            return;
        }
        else {
            session.setAttribute("customerIsAuthenticated", "true");
            session.setAttribute("customerName", accountCustomer.getCustomer().getName());
            session.setAttribute("customerAddress", accountCustomer.getCustomer().getAddress());
            session.setAttribute("customerId", accountCustomer.getCustomer().getId());
            session.setAttribute("customerPhone", accountCustomer.getCustomer().getPhone());
            session.setAttribute("accountCustomerId", accountCustomer.getId());
            session.setAttribute("cart", new ArrayList<>());
            response.sendRedirect("/book-store/show-all-book-available");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
