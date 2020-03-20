package model.dao.impl;

import com.mysql.cj.protocol.Resultset;
import model.connectdb.ConnectDB;
import model.dao.CustomerDAO;
import model.entity.Book;
import model.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static ConnectDB connectDB = ConnectDB.getInstance();
    @Override
    public List<Customer> getAll() {
        List<Customer> result = new ArrayList<>();

        String query = "SELECT * FROM customer";

        try {
            PreparedStatement statement= connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Customer cus = new Customer();
                cus.setId(rs.getInt("id"));
                cus.setIdCard(rs.getString("id_card"));
                cus.setAddress(rs.getString("address"));
                cus.setName(rs.getString("name"));
                cus.setPhone(rs.getString("phone"));
                result.add(cus);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public Customer getById(Integer id) {
        Customer result = new Customer();

        String query = "SELECT * FROM customer WHERE id=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setIdCard(rs.getString("id_card"));
                result.setAddress((rs.getString("address")));
                result.setName(rs.getString("name"));
                result.setPhone(rs.getString("phone"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public boolean create(Customer customer) {
        String query = "INSERT INTO customer(id_card,name, address,phone) VALUE(?,?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1,customer.getIdCard());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        Customer b = getById(id);
        if(customer.getId() == null) return null ;

        String query = "UPDATE customer SET name=?,address = ?,phone = ? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPhone());
            statement.setInt(4,id);
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        Customer cus = getById(id);
        if(cus.getId() == null) return false;

        String query = "DELETE FROM customer WHERE id=?";
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
    public Customer getByIdCard(String idCard) {
        Customer result = new Customer();

        String query = "SELECT * FROM customer WHERE id_card=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, idCard);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setIdCard(rs.getString("id_card"));
                result.setAddress((rs.getString("address")));
                result.setName(rs.getString("name"));
                result.setPhone(rs.getString("phone"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

//    public static void main(String[] args) {
//        Customer vanh = new Customer(3,"c102","viet anh","ao sen","01234");
//        CustomerDAOImpl daotest = new CustomerDAOImpl();
//        System.out.println(daotest.create(vanh));
//    }

}

