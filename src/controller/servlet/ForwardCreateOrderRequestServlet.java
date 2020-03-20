package controller.servlet;

import model.dao.OrderBillDAO;
import model.dao.OrderedItemDAO;
import model.dao.impl.OrderBillDAOImpl;
import model.dao.impl.OrderedItemDAOImpl;
import model.entity.OrderedItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ForwardCreateOrderRequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
        if(cart.size() > 0) {
            response.sendRedirect("create_order.jsp");
        }
        else  {
            response.sendRedirect("error_cart_empty.jsp");
        }
    }
}
