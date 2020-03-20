package model.dao;

import model.entity.OrderBill;

public interface OrderBillDAO extends BaseDAO<OrderBill> {
        OrderBill updateStaffCodeAndNameInBill(Integer id, OrderBill orderBill);
}
