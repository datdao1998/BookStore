package controller.service.impl;
// Author: anhnv

import controller.service.OrderedItemService;
import model.dao.OrderedItemDAO;
import model.dao.impl.OrderedItemDAOImpl;
import model.entity.OrderedItem;

import java.util.ArrayList;

public class OrderedItemServiceImpl implements OrderedItemService {
    OrderedItemDAO orderedItemDAO = new OrderedItemDAOImpl();

    @Override
    public boolean create(OrderedItem orderedItem) {
        return orderedItemDAO.create(orderedItem);
    }

    @Override
    public boolean delete(Integer id) {
        return orderedItemDAO.delete(id);
    }

    @Override
    public ArrayList<OrderedItem> getAllByOrderId(int id) {
        OrderedItemDAO orderedItemDAO = new OrderedItemDAOImpl();
        ArrayList<OrderedItem> orderedItemArrayList = (ArrayList<OrderedItem>) orderedItemDAO.getAllByOrderId(id);
        return orderedItemArrayList;
    }


}
