package controller.servlet;

import controller.service.AccountStaffService;
import controller.service.impl.AccountStaffServiceImpl;
import model.entity.AccountStaff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StaffLoginServlet")
public class StaffLoginServlet extends HttpServlet {
    private AccountStaffService accountStaffService = new AccountStaffServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("staff-username");
        String password = request.getParameter("staff-password");
        HttpSession session = request.getSession();
        AccountStaff accountStaff = accountStaffService.authentication(userName, password);
        if (accountStaff.getId() == null) {
            session.setAttribute("staffIsAuthenticated", "false");
            request.setAttribute("staffAuthenticationMessage", "User name or password is invalid");
            RequestDispatcher dispatcher = request.getRequestDispatcher("staff_login.jsp");
            dispatcher.forward(request,response);
            return;
        }
        else {
            session.setAttribute("staffIsAuthenticated", "true");
            session.setAttribute("staffCode", accountStaff.getStaff().getStaffCode());
            session.setAttribute("staffName",accountStaff.getStaff().getName());
            session.setAttribute("accountStaffId", accountStaff.getId());
            response.sendRedirect("/book-store/staff_home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
