package model.dao;

import model.entity.AccountStaff;

public interface AccountStaffDAO extends BaseDAO<AccountStaff> {
    AccountStaff getByUserName(String userName);
    boolean deleteAccountStaff(String userName);
}
