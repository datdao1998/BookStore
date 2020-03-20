package controller.servlet;

import controller.service.OrderBillService;
import controller.service.impl.OrderBillServiceImpl;
import model.entity.OrderBill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CheckNewOrderBillServlet")
public class CheckNewOrderBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String t = request.getParameter("length");
            OrderBillService orderBillService = new OrderBillServiceImpl();
            ArrayList<OrderBill> orderBillArrayList = (ArrayList<OrderBill>) orderBillService.getAllByNotInStatus();
            if(orderBillArrayList.size()!=Integer.parseInt(t)){
                response.getWriter().print("true");
            }
            else {
                response.getWriter().print("false");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
