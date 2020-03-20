package controller.servlet;

import model.dao.OrderBillDAO;
import model.dao.impl.OrderBillDAOImpl;
import model.entity.OrderBill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OrderUpdateServlet")
public class OrderUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String update = request.getParameter("update");
            String cancel = request.getParameter("cancel");
            if(update==null){
                HttpSession session = request.getSession();
                session.removeAttribute("orderId");
                response.sendRedirect("staff_home.jsp");
            }
            else{
                HttpSession session = request.getSession();
                String staffName = (String) session.getAttribute("staffName");
                String staffCode = (String) session.getAttribute("staffCode");
                OrderBillDAO orderBillDAO = new OrderBillDAOImpl();
                OrderBill orderBill = orderBillDAO.getById((Integer) session.getAttribute("orderId"));
                orderBill.setStaffCode(staffCode);
                orderBill.setStaffName(staffName);
                orderBillDAO.updateStaffCodeAndNameInBill(orderBill.getId(),orderBill);
                if(orderBill.getStatus().equals(OrderBill.STATUS.SHIPPING)){
                    orderBill.setStatus(OrderBill.STATUS.DONE);
                    orderBillDAO.update(orderBill.getId(),orderBill);
                    response.sendRedirect("order_detail.jsp");
                }
                if(orderBill.getStatus().equals(OrderBill.STATUS.CONFIRMED)){
                    orderBill.setStatus(OrderBill.STATUS.SHIPPING);
                    orderBillDAO.update(orderBill.getId(),orderBill);
                    response.sendRedirect("order_detail.jsp");
                }
                if(orderBill.getStatus().equals(OrderBill.STATUS.WAIT_CONFIRM)){
                    orderBill.setStatus(OrderBill.STATUS.CONFIRMED);
                    orderBillDAO.update(orderBill.getId(),orderBill);
                    response.sendRedirect("order_detail.jsp");
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
