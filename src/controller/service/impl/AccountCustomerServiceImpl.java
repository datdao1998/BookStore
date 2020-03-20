package controller.service.impl;

import controller.service.AccountCustomerService;
import controller.service.CustomerService;
import dto.CreateAccountCustomerReq;
import model.dao.AccountCustomerDAO;
import model.dao.CustomerDAO;
import model.dao.impl.AccountCustomerDAOImpl;
import model.entity.AccountCustomer;
import model.entity.Customer;

public class AccountCustomerServiceImpl implements AccountCustomerService {

    private AccountCustomerDAO accountCustomerDAO = new AccountCustomerDAOImpl();
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    public AccountCustomer authentication(String userName, String password) {
        AccountCustomer accountCustomer = accountCustomerDAO.getByUserName(userName);
        if(accountCustomer.getId() != null) {
            if(!(accountCustomer.getPassWord().equals(password))) {
                return new AccountCustomer();
            }
        }
        return accountCustomer;
    }

    @Override
    public String checkUserNameExisted(String userName) {
        AccountCustomer accountCustomer = accountCustomerDAO.getByUserName(userName);
        if(accountCustomer.getId() != null) return "User name is existed. Please choose another user name!";
        else return "User name is valid!.";
    }

    @Override
    public boolean create(CreateAccountCustomerReq req) {
        Customer customer = customerService.findByIdCard(req.getIdCard());

        if(customer.getId() != null) {
            AccountCustomer accountCustomer = new AccountCustomer();
            accountCustomer.setCustomer(customer);
            accountCustomer.setUserName(req.getUserName());
            accountCustomer.setPassWord(req.getPassword());
            return accountCustomerDAO.create(accountCustomer);
        }
        else {
            Customer cus = new Customer();
            cus.setIdCard(req.getIdCard());
            cus.setName(req.getName());
            cus.setAddress(req.getAddress());
            cus.setPhone(req.getPhone());
            Customer customer1 = customerService.create(cus);
            AccountCustomer accountCustomer = new AccountCustomer();
            accountCustomer.setCustomer(customer1);
            accountCustomer.setUserName(req.getUserName());
            accountCustomer.setPassWord(req.getPassword());
            return accountCustomerDAO.create(accountCustomer);
        }

    }
}
