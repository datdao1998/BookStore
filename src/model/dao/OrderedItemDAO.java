package model.dao;

import model.entity.OrderedItem;

import java.util.List;

public interface OrderedItemDAO extends BaseDAO<OrderedItem> {
    public List<OrderedItem> getAllByOrderId(int id);

}
