package model.dao;

import model.entity.AccountCustomer;

public interface AccountCustomerDAO extends BaseDAO<AccountCustomer> {
    AccountCustomer getByUserName(String userName);
    boolean deleteAccountCustomer(String userName);
}
