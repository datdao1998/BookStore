package controller.servlet;

import controller.service.OrderBillService;
import controller.service.impl.OrderBillServiceImpl;
import model.dao.OrderBillDAO;
import model.dao.OrderedItemDAO;
import model.dao.impl.OrderBillDAOImpl;
import model.dao.impl.OrderedItemDAOImpl;
import model.entity.Customer;
import model.entity.OrderBill;
import model.entity.OrderedItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderServlet extends HttpServlet {

    OrderBillService orderBillService = new OrderBillServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        boolean isExecuted = orderBillService.create(session);
        if(isExecuted) {
            response.sendRedirect("create_order_done.jsp");
        }
        else {
            response.sendRedirect("error_create_order.jsp");
        }
    }
}
