package controller.servlet;

import model.dao.impl.BookDAOImpl;
import model.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBookToDatabaseServlet")
public class AddBookToDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            BookDAOImpl bookDAO = new BookDAOImpl();
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String publisher = request.getParameter("publisher");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Book book = new Book(null,name,author,publisher,category,price,quantity);
            bookDAO.create(book);
            response.sendRedirect("order_update_success.jsp");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
