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

@WebServlet(name = "CreatOrderBillServlet")
public class CreatOrderBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                OrderBillService orderBillService = new OrderBillServiceImpl();
                ArrayList<OrderBill> orderBillArrayList = orderBillService.getAll();
                HttpSession session = request.getSession();
                session.setAttribute("listOrderBill",orderBillArrayList);
                response.sendRedirect("list_order_bill.jsp");
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
