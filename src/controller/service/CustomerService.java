package controller.service;

import model.entity.Customer;

public interface CustomerService {

    Customer findByIdCard(String idCard);

    Customer create(Customer customer);
}
