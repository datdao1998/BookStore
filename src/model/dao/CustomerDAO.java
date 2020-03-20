package model.dao;

import model.entity.Customer;

public interface CustomerDAO extends BaseDAO<Customer> {
    Customer getByIdCard(String idCard);
}
