package model.dao;

import model.entity.Book;

import java.util.List;

public interface BookDAO extends BaseDAO<Book> {
        List<Book> searchBookByName(String name);

        Book updateAvailable(Integer id, Integer available);
}
