package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.domain.Order;
import com.jpetstore.servlet.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class viewListOrderServlet extends HttpServlet {
    private Account account;
    private List<Order> orderList;
    private static final String VIEW_LISTORDER="/WEB-INF/jsp/order/ListOrders.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        OrderService orderService=new OrderService();
        account=(Account) session.getAttribute("account");
        orderList=orderService.getListOrder(account.getUsername());
        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(VIEW_LISTORDER).forward(request,response);
    }
}
