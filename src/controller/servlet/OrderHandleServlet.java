package controller.servlet;

import controller.service.OrderBillService;
import controller.service.impl.OrderBillServiceImpl;
import model.entity.OrderBill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHandleServlet")
public class OrderHandleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            OrderBillService orderBillService = new OrderBillServiceImpl();
            List<OrderBill> list = orderBillService.getAllByNotInStatus();
            session.setAttribute("listOrderBill",list);
            response.sendRedirect("order_handle.jsp");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
