package controller.servlet;

import controller.service.BookService;
import controller.service.impl.BookServiceImpl;
import model.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookName = request.getParameter("book-name-search");
        List<Book> books = bookService.searchAllBookAvailableByName(bookName);
        request.setAttribute("listBook", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("show_books.jsp");
        dispatcher.forward(request, response);
    }
}
