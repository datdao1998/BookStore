package controller.service.impl;

import controller.service.BookService;
import model.dao.BookDAO;
import model.dao.impl.BookDAOImpl;
import model.entity.Book;
import model.entity.OrderedItem;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public List<Book> getAllBookAvailable() {
        List<Book> books = bookDAO.getAll();
        List<Book> bookList = new ArrayList<>();
        books.forEach(book -> {
            if (book.getAvailable() > 0) bookList.add(book);
        });
        return bookList;
    }

    @Override
    public List<Book> searchAllBookAvailableByName(String name) {

        List<Book> books = new ArrayList<>();
        List<Book> bookList = bookDAO.searchBookByName(name);
        bookList.forEach(book -> {
            if(book.getAvailable() > 0) books.add(book);
        });

        return books;
    }

    @Override
    public Book findById(Integer id) {
        return bookDAO.getById(id);
    }

    @Override
    public List<OrderedItem> addToCart(Integer bookId, List<OrderedItem> cart) {

        Book book = findById(bookId);

        for( int i = 0; i< cart.size(); i ++) {
            if(cart.get(i).getBook().getId().equals(book.getId())) {
                Integer quantity = cart.get(i).getQuantity();
                cart.get(i).setQuantity(quantity + 1);
                return cart;
            }
        }
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setQuantity(1);
        orderedItem.setBook(book);
        cart.add(orderedItem);
        return cart;
    }

    @Override
    public Book updateAvailable(OrderedItem orderedItem) {

        Book book = orderedItem.getBook();
        Integer quantity = orderedItem.getQuantity();
        Integer available = book.getAvailable();
        book.setAvailable(available - quantity);
        return bookDAO.update(book.getId(), book);
    }


}
