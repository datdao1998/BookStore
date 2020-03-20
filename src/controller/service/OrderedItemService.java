package controller.service;
// Author: anhnv

import model.entity.OrderedItem;

import java.util.ArrayList;

public interface OrderedItemService {

    boolean create(OrderedItem orderedItem);

    boolean delete(Integer id);

    ArrayList<OrderedItem> getAllByOrderId(int id);


}
