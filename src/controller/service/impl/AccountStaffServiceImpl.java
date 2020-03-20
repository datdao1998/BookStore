package controller.service.impl;

import controller.service.AccountStaffService;
import model.dao.impl.AccountStaffDAOImpl;
import model.entity.AccountStaff;

public class AccountStaffServiceImpl implements AccountStaffService {
    private AccountStaffDAOImpl accountStaffDAO = new AccountStaffDAOImpl();
    @Override
    public AccountStaff authentication(String userName, String password) {
        AccountStaff accountStaff = accountStaffDAO.getByUserName(userName);
        if(accountStaff.getId()!=null) {
            if (!(accountStaff.getPassWord().equals(password))) {
                return new AccountStaff();
            }
        }
        return accountStaff;
    }

}
