package controller.service;

import model.entity.AccountStaff;

public interface AccountStaffService {
    AccountStaff authentication(String userName, String password);

}
