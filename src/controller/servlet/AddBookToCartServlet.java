package controller.servlet;

import controller.service.BookService;
import controller.service.impl.BookServiceImpl;
import model.entity.OrderedItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddBookToCartServlet")
public class AddBookToCartServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<>());
        }

        List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");

        Integer bookId = Integer.parseInt(request.getParameter("book-id"));

        cart = bookService.addToCart(bookId, cart);

        session.setAttribute("cart", cart);

        String searchInput = request.getParameter("book-name");
        request.setAttribute("book-search-name", searchInput);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/search-book");
        dispatcher.forward(request, response);
    }
}
