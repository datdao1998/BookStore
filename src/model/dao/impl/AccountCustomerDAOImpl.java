package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.AccountCustomerDAO;
import model.dao.CustomerDAO;
import model.entity.AccountCustomer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountCustomerDAOImpl implements AccountCustomerDAO {

    private static ConnectDB connectDB = ConnectDB.getInstance();

    @Override
    public AccountCustomer getByUserName(String userName) {
        AccountCustomer result = new AccountCustomer();

        String query = "SELECT * FROM account_customer WHERE username = ?";

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setUserName(rs.getString("username"));
                result.setPassWord(rs.getString("password"));
                result.setCustomer(customerDAO.getById(rs.getInt("customer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteAccountCustomer(String userName) {
        AccountCustomer accountCustomer = getByUserName(userName);
        if(accountCustomer == null) return false;

        String query = "DELETE FROM account_customer WHERE username=?";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public List<AccountCustomer> getAll() {
        List<AccountCustomer>  result = new ArrayList<>();

        String query = "SELECT * FROM account_customer";

        CustomerDAOImpl dao = new CustomerDAOImpl();
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                AccountCustomer accountCustomer = new AccountCustomer();
                accountCustomer.setId(rs.getInt("id"));
                accountCustomer.setUserName(rs.getString("username"));
                accountCustomer.setPassWord(rs.getString("password"));
                accountCustomer.setCustomer(dao.getById(rs.getInt("customer_id")));
                result.add(accountCustomer);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;

    }

    @Override
    public AccountCustomer getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(AccountCustomer accountCustomer) {
        String query = "INSERT INTO account_customer(username, password, customer_id) VALUE(?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1,accountCustomer.getUserName());
            statement.setString(2,accountCustomer.getPassWord());
            statement.setInt(3,accountCustomer.getCustomer().getId());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Override
    public AccountCustomer update(Integer id, AccountCustomer accountCustomer) {
        AccountCustomer b = getById(id);
        if(accountCustomer.getId() == null) return null;

        String query = "UPDATE account_customer SET password = ? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, accountCustomer.getPassWord());
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);

    }

    @Override
    public boolean delete(Integer id) {
        return true;
    }

//    public static void main(String[] args) {
//        AccountCustomerDAOImpl dao = new AccountCustomerDAOImpl();
//        System.out.println(dao.deleteAccountCustomer("datntkute"));
//    }

}
