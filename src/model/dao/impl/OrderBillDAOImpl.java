package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.CustomerDAO;
import model.dao.OrderBillDAO;
import model.entity.OrderBill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderBillDAOImpl implements OrderBillDAO {

    private static ConnectDB connectDB = ConnectDB.getInstance();

    @Override
    public List<OrderBill> getAll() {
        List<OrderBill> result = new ArrayList<>();

        CustomerDAO customerDAO = new CustomerDAOImpl();
        String query = "SELECT * FROM order_bill";

        try {
            PreparedStatement statement= connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
               OrderBill orderedItem = new OrderBill();
                orderedItem.setId(rs.getInt("id"));
                orderedItem.setCustomer(customerDAO.getById(rs.getInt("customer_id")));
                orderedItem.setStatus(rs.getString("status"));
                orderedItem.setStaffCode(rs.getString("staff_code"));
                orderedItem.setStaffName(rs.getString("staff_name"));
                result.add(orderedItem);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public OrderBill getById(Integer id) {
        OrderBill result = new OrderBill();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        String query = "SELECT * FROM order_bill WHERE id=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setStatus(rs.getString("status"));
                result.setCustomer(customerDAO.getById(rs.getInt("customer_id")));
                result.setStaffCode(rs.getString("staff_code"));
                result.setStaffName(rs.getString("staff_name"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public boolean create(OrderBill orderBill) {
        String query = "INSERT INTO order_bill(customer_id, status, staff_code, staff_name, id) VALUE(?,?,?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, orderBill.getCustomer().getId());
            statement.setString(2, orderBill.getStatus());
            statement.setString(3, orderBill.getStaffCode());
            statement.setString(4, orderBill.getStaffName());
            statement.setInt(5, orderBill.getId());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public OrderBill update(Integer id, OrderBill orderBill) {

        if (orderBill == null) return null;

        String query = "UPDATE order_bill SET status=? WHERE id=?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, orderBill.getStatus());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        OrderBill orderBill = getById(id);
        if(orderBill.getId() == null) return false;

        String query = "DELETE FROM order_bill WHERE id=?";
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
    public OrderBill updateStaffCodeAndNameInBill(Integer id, OrderBill orderBill) {
        if (orderBill == null) return null;

        String query = "UPDATE order_bill SET staff_code=?,staff_name=? WHERE id=?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, orderBill.getStaffCode());
            statement.setString(2,orderBill.getStaffName());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getById(id);
    }
}
