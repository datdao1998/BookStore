package model.dao;

import model.entity.Staff;

public interface StaffDAO extends BaseDAO <Staff>{
    Staff getByStaffCode(String code);
}
