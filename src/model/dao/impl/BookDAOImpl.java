package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.BookDAO;
import model.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private static ConnectDB connectDB = ConnectDB.getInstance();

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();

        String query = "SELECT * FROM book";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setAvailable(rs.getInt("available"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getDouble("price"));
                book.setPublisher(rs.getString("publisher"));
                book.setCategory(rs.getString("category"));
                result.add(book);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public Book getById(Integer id) {

        Book result = new Book();

        String query = "SELECT * FROM book WHERE id=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setAuthor(rs.getString("author"));
                result.setAvailable(rs.getInt("available"));
                result.setName(rs.getString("name"));
                result.setPrice(rs.getDouble("price"));
                result.setPublisher(rs.getString("publisher"));
                result.setCategory(rs.getString("category"));

            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public boolean create(Book book) {

        String query = "INSERT INTO book(name, author, publisher, price, available, category) VALUE(?,?,?,?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getAvailable());
            statement.setString(6, book.getCategory());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Book update(Integer id, Book book) {

        Book b = getById(id);
        if(book.getId() == null) return null;

        String query = "UPDATE book SET name=?,publisher=?,author=?,price=?,available=?,category=? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getPublisher());
            statement.setString(3, book.getAuthor());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getAvailable());
            statement.setInt(7, id);
            statement.setString(6, book.getCategory());

            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public boolean delete(Integer id) {

        Book book = getById(id);
        if(book.getId() == null) return false;

        String query = "DELETE FROM book WHERE id=?";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Book> searchBookByName(String name) {

        if(name == null) return getAll();

        List<Book> listBooks = new ArrayList<>();
        String query = "SELECT * FROM book WHERE (name LIKE '%" + name +"%')";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setAvailable(rs.getInt("available"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getDouble("price"));
                book.setPublisher(rs.getString("publisher"));
                book.setCategory(rs.getString("category"));
                listBooks.add(book);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return  listBooks;
    }

    @Override
    public Book updateAvailable(Integer id, Integer available) {
        Book b = getById(id);
        if(b.getId() == null) return null;

        String query = "UPDATE book SET available=? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, available);
            statement.setInt(2, id);

            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);
    }

//    public static void main(String[] args) {
//        BookDAOImpl dao = new BookDAOImpl();
//        List<Book> books = dao.searchBookByName("hi");
//        for(Book i: books) System.out.println(i.getName());
//    }
}
