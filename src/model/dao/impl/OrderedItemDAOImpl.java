package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.BookDAO;
import model.dao.OrderBillDAO;
import model.dao.OrderedItemDAO;
import model.entity.OrderedItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderedItemDAOImpl implements OrderedItemDAO {

    private static ConnectDB connectDB =  ConnectDB.getInstance();

    @Override
    public List<OrderedItem> getAll() {
        List<OrderedItem> result = new ArrayList<>();

        BookDAO bookDAO = new BookDAOImpl();
        OrderBillDAO orderBillDAO = new OrderBillDAOImpl();

        String query = "SELECT * FROM ordered_item";

        try {
            PreparedStatement statement= connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setId(rs.getInt("id"));
                orderedItem.setBook(bookDAO.getById(rs.getInt("book_id")));
                orderedItem.setOrderBill(orderBillDAO.getById(rs.getInt("order_id")));
                orderedItem.setQuantity(rs.getInt("quantity"));
                result.add(orderedItem);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public OrderedItem getById(Integer id) {
        OrderedItem result = new OrderedItem();

        String query = "SELECT * FROM ordered_item WHERE id=?";
        BookDAO bookDAO = new BookDAOImpl();
        OrderBillDAO orderBillDAO = new OrderBillDAOImpl();

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setBook(bookDAO.getById(rs.getInt("book_id")));
                result.setOrderBill(orderBillDAO.getById(rs.getInt("order_id")));
                result.setQuantity(rs.getInt("quantity"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public boolean create(OrderedItem orderedItem) {
        String query = "INSERT INTO ordered_item(book_id,quantity,order_id) VALUE(?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1,orderedItem.getBook().getId());
            statement.setInt(2,orderedItem.getQuantity());
            statement.setInt(3,orderedItem.getOrderBill().getId());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public OrderedItem update(Integer id, OrderedItem orderedItem) {
        OrderedItem b = getById(id);
        if(orderedItem.getId() == null) return null ;

        String query = "UPDATE ordered_item SET book_id = ?, quantity = ?, order_id = ? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1,orderedItem.getBook().getId());
            statement.setInt(2,orderedItem.getQuantity());
            statement.setInt(3,orderedItem.getOrderBill().getId());
            statement.setInt(4,id);
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        OrderedItem orderedItem = getById(id);
        if(orderedItem.getId() == null) return false;

        String query = "DELETE FROM ordered_item WHERE id=?";
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
    public List<OrderedItem> getAllByOrderId(int orderId) {
        String query = "SELECT * FROM ordered_item WHERE order_id=?";
        List<OrderedItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(query);
            ps.setInt(1,orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BookDAOImpl bookDAO = new BookDAOImpl();
                OrderBillDAOImpl orderBillDAO = new OrderBillDAOImpl();
                OrderedItem element = new OrderedItem();
                element.setId(rs.getInt("id"));
                element.setBook(bookDAO.getById(rs.getInt("book_id")));
                element.setQuantity(rs.getInt("quantity"));
                element.setOrderBill(orderBillDAO.getById(orderId));
                list.add(element);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
