package controller.service;

import model.entity.Book;
import model.entity.OrderedItem;

import java.util.List;

public interface BookService {

    List<Book> getAllBookAvailable();

    List<Book> searchAllBookAvailableByName(String name);

    Book findById(Integer id);

    List<OrderedItem> addToCart(Integer bookId, List<OrderedItem> cart);

    Book updateAvailable(OrderedItem orderedItem);
}
