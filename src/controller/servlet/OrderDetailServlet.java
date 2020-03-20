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
import java.util.ArrayList;

@WebServlet(name = "OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("orderId"));
            HttpSession session = request.getSession();
            OrderBillService orderBillService = new OrderBillServiceImpl();
            ArrayList<OrderBill> orderBillArrayList = orderBillService.getAll();
            session.setAttribute("listOrderBill",orderBillArrayList);
            session.setAttribute("orderId",id);
            response.sendRedirect("/book-store/order_detail.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
