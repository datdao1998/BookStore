package controller.servlet;

import controller.service.OrderBillService;
import controller.service.OrderedItemService;
import controller.service.impl.OrderBillServiceImpl;
import controller.service.impl.OrderedItemServiceImpl;
import model.entity.OrderBill;
import model.entity.OrderedItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderBillDetailServlet")
public class OrderBillDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            OrderBillService orderBillService = new OrderBillServiceImpl();
            OrderedItemService orderedItemService = new OrderedItemServiceImpl();
            int id = Integer.parseInt(request.getParameter("orderBillId"));
            OrderBill orderBill = orderBillService.getOne(id);
            HttpSession session = request.getSession();
            session.setAttribute("Order_bill_to_detail",orderBill);
            ArrayList<OrderedItem> orderedItemArrayList = orderedItemService.getAllByOrderId(id);
            session.setAttribute("list_ordered_searched_by_orderId",orderedItemArrayList);
            response.sendRedirect("order_bill_detail.jsp");

        }catch (Exception e){

        }

    }
}
