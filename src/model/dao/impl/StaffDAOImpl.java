package model.dao.impl;

import model.connectdb.ConnectDB;
import model.dao.BaseDAO;
import model.dao.StaffDAO;
import model.entity.Customer;
import model.entity.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    private static ConnectDB connectDB =  ConnectDB.getInstance();

    @Override
    public List<Staff> getAll() {
        List<Staff> result = new ArrayList<>();

        String query = "SELECT * FROM staff";

        try {
            PreparedStatement statement= connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Staff cus = new Staff();
                cus.setId(rs.getInt("id"));
                cus.setStaffCode(rs.getString("staff_code"));
                cus.setName(rs.getString("name"));
                cus.setPhone(rs.getString("phone"));
                result.add(cus);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public Staff getById(Integer id) {
        Staff result = new Staff();

        String query = "SELECT * FROM staff WHERE id=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setStaffCode((rs.getString("staff_code")));
                result.setName(rs.getString("name"));
                result.setPhone(rs.getString("phone"));


            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public boolean create(Staff staff) {
        String query = "INSERT INTO staff(name, staff_code,phone) VALUE(?,?,?)";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getStaffCode());
            statement.setString(3, staff.getPhone());
            statement.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Staff update(Integer id, Staff staff) {
        Staff b = getById(id);
        if(staff.getId() == null) return null ;

        String query = "UPDATE staff SET name=?,staff_code = ?,phone = ? WHERE id=?";
        try{
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getStaffCode());
            statement.setString(3, staff.getPhone());
            statement.setInt(4,id);

            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        Staff sta = getById(id);
        if(sta.getId() == null) return false;

        String query = "DELETE FROM staff WHERE id=?";
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
    public Staff getByStaffCode(String staffCode) {
        Staff result = new Staff();

        String query = "SELECT * FROM staff WHERE staff_code=?";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, staffCode);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.setId(rs.getInt("id"));
                result.setStaffCode((rs.getString("staff_code")));
                result.setName(rs.getString("name"));
                result.setPhone(rs.getString("phone"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

//    public static void main(String[] args) {
//        StaffDAOImpl daostaff = new StaffDAOImpl();
//        System.out.println(daostaff.getByStaffCode("S300").getName());
//    }
}
