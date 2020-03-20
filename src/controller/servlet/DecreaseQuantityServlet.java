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

@WebServlet(name = "DecreaseQuantityServlet")
public class DecreaseQuantityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
        Integer index = Integer.parseInt(request.getParameter("index"));
        int quantity = cart.get(index).getQuantity();
        quantity = quantity - 1;
        if (quantity <= 0) {
            cart.removeIf(orderedItem -> {
                if (cart.get(index).getBook().getId().equals(orderedItem.getBook().getId())) {
                    return true;
                };
                return false;
            });
        }
        else {
            cart.get(index).setQuantity(quantity);
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("cart_detail.jsp");
    }
}
