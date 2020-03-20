package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.AccountStaffDAO;
import model.entity.AccountStaff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountStaffDAOImpl implements AccountStaffDAO {

    private static ConnectDB connectDB = ConnectDB.getInstance();

    @Override
    public AccountStaff getByUserName(String userName) {
        AccountStaff result = new AccountStaff();

        String query = "SELECT * FROM account_staff WHERE username = ?";

        StaffDAOImpl staffDAO = new StaffDAOImpl();

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setUserName(rs.getString("username"));
                result.setPassWord(rs.getString("password"));
                result.setStaff(staffDAO.getById(rs.getInt("staff_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteAccountStaff(String userName) {
        AccountStaff accountStaff = getByUserName(userName);
        if(accountStaff == null) return false;

        String query = "DELETE FROM account_staff WHERE username=?";
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
    public List<AccountStaff> getAll() {
        List<AccountStaff>  result = new ArrayList<>();

        String query = "SELECT * FROM account_staff";

        StaffDAOImpl dao = new StaffDAOImpl();
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                AccountStaff accountStaff = new AccountStaff();
                accountStaff.setId(rs.getInt("id"));
                accountStaff.setUserName(rs.getString("username"));
                accountStaff.setPassWord(rs.getString("password"));
                accountStaff.setStaff(dao.getById(rs.getInt("staff_id")));
                result.add(accountStaff);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public AccountStaff getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(AccountStaff accountStaff) {
        String query = "INSERT INTO account_staff(username, password, staff_id) VALUE(?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1,accountStaff.getUserName());
            statement.setString(2,accountStaff.getPassWord());
            statement.setInt(3,accountStaff.getStaff().getId());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public AccountStaff update(Integer id, AccountStaff accountStaff) {
        AccountStaff b = getById(id);
        if(accountStaff.getId() == null) return null;

        String query = "UPDATE account_staff SET password = ? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, accountStaff.getPassWord());
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
//          AccountStaffDAOImpl dao = new AccountStaffDAOImpl();
//        StaffDAOImpl staffDAO = new StaffDAOImpl();
//        AccountStaff accountStaff = new AccountStaff();
//        accountStaff.setId(1);
//        accountStaff.setUserName("daoquocdatz1x2");
//        accountStaff.setPassWord("aadas");
//        accountStaff.setStaff(staffDAO.getById(1));
//        System.out.println(dao.update(1,accountStaff));
//    }
}
