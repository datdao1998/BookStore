package controller.service.impl;

import controller.service.CustomerService;
import model.dao.CustomerDAO;
import model.dao.impl.CustomerDAOImpl;
import model.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public Customer findByIdCard(String idCard) {
        return customerDAO.getByIdCard(idCard);
    }

    @Override
    public Customer create(Customer customer) {
        boolean isExecuted = customerDAO.create(customer);
        if(isExecuted) return findByIdCard(customer.getIdCard());
        else return null;
    }
}
