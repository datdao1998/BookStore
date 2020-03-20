package controller.service;
// Author: anhnv

import model.entity.OrderBill;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface OrderBillService {

    boolean create(HttpSession session);

    ArrayList<OrderBill> getAll();

    OrderBill getOne(int id);

    List<OrderBill> getAllByNotInStatus();
}
