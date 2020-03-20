package controller.service.impl;
// Author: anhnv

import controller.service.BookService;
import controller.service.OrderBillService;
import controller.service.OrderedItemService;
import model.dao.OrderBillDAO;
import model.dao.impl.OrderBillDAOImpl;
import model.entity.Customer;
import model.entity.OrderBill;
import model.entity.OrderedItem;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class OrderBillServiceImpl implements OrderBillService {

    OrderBillDAO orderBillDAO = new OrderBillDAOImpl();
    OrderedItemService orderedItemService = new OrderedItemServiceImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public boolean create(HttpSession session) {
        List<OrderedItem> cart = (List<OrderedItem>) session.getAttribute("cart");
        Customer customer = new Customer();
        customer.setId((Integer) session.getAttribute("customerId"));
        List<OrderBill> list = orderBillDAO.getAll();
        Integer id = list.size() + 1;
        OrderBill orderBill = new OrderBill();
        orderBill.setId(id);
        orderBill.setCustomer(customer);
        orderBill.setStatus(OrderBill.STATUS.WAIT_CONFIRM);
        boolean isCreated = orderBillDAO.create(orderBill);

        if (isCreated) {
            for (int i = 0; i < cart.size(); i++) {
                OrderedItem orderedItem = cart.get(i);
                orderedItem.setOrderBill(orderBill);
                try {
                    orderedItemService.create(orderedItem);
                } catch (Exception e) {
                    orderBillDAO.delete(id);
                    return false;
                }
                bookService.updateAvailable(orderedItem);
            }
            session.setAttribute("cart", new ArrayList<>());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<OrderBill> getAll() {
        OrderBillDAO orderBillDAO = new OrderBillDAOImpl();
        ArrayList<OrderBill> orderBillArrayList = (ArrayList<OrderBill>) orderBillDAO.getAll();
        return orderBillArrayList;
    }

    @Override
    public OrderBill getOne(int id) {
        OrderBillDAO orderBillDAO = new OrderBillDAOImpl();
        OrderBill orderBill = orderBillDAO.getById(id);
        return  orderBill;
    }

    @Override
    public List<OrderBill> getAllByNotInStatus() {
        OrderBillDAO orderBillDAO = new OrderBillDAOImpl();
        List<OrderBill> orderBills = orderBillDAO.getAll();
        orderBills.removeIf(orderBill -> {
            if(orderBill.getStatus().equals(OrderBill.STATUS.DONE))
                return true;
            else return false;
        });
        return orderBills;
    }
}
