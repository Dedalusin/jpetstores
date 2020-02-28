package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Order;
import com.jpetstore.servlet.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class viewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER="/WEB-INF/jsp/order/ViewOrder.jsp";
    private int orderId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        OrderService orderService=new OrderService();
        orderId=Integer.parseInt(request.getParameter("orderId"));
        Order order=orderService.getOrder(orderId);
        session.setAttribute("order",order);
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
