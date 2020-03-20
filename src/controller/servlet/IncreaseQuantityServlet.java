package controller.servlet;

import model.entity.OrderedItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IncreaseQuantityServlet")
public class IncreaseQuantityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
        Integer index = Integer.parseInt(request.getParameter("index"));

        OrderedItem orderedItem = cart.get(index);
        int quantity = orderedItem.getQuantity();
        int available = orderedItem.getBook().getAvailable();
        if (quantity < available) {
            cart.get(index).setQuantity(quantity + 1);
            session.setAttribute("cart", cart);
            response.sendRedirect("cart_detail.jsp");
        }
        else {
            response.sendRedirect("cart_detail.jsp");
        }
    }
}
