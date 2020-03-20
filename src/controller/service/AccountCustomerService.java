package controller.service;

import dto.CreateAccountCustomerReq;
import model.entity.AccountCustomer;

public interface AccountCustomerService {

    AccountCustomer authentication(String userName, String password);

    String checkUserNameExisted(String userName);

    boolean create(CreateAccountCustomerReq req);
}
