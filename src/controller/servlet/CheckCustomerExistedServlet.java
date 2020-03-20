package controller.servlet;

import com.google.gson.Gson;
import controller.service.CustomerService;
import controller.service.impl.CustomerServiceImpl;
import model.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckCustomerExistedServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idCard = request.getParameter("id-card");
        Customer customer = customerService.findByIdCard(idCard);
        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(customer));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
