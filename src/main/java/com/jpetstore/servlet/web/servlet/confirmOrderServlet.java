package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.domain.Cart;
import com.jpetstore.servlet.domain.Order;
import com.jpetstore.servlet.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class confirmOrderServlet extends HttpServlet {
    private static final String SHIPPINGFORM="/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";
    private static final String CONFIRM="/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String VIEW_ORDER="/WEB-INF/jsp/order/ViewOrder.jsp";
    String shippingAddressRequired;
    Boolean shippingAddressRequired_boolean;
    private Order order;
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        order=(Order) session.getAttribute("order");
        OrderService orderService=new OrderService();
        shippingAddressRequired=request.getParameter("shippingAddressRequired");
        shippingAddressRequired_boolean=Boolean.valueOf(shippingAddressRequired).booleanValue();
        if (shippingAddressRequired_boolean) {
            shippingAddressRequired_boolean = false;
            request.getRequestDispatcher(SHIPPINGFORM).forward(request,response);
        } else if (order==null||order.isConfirmed()) {
            order=new Order();
            Cart cart=(Cart) session.getAttribute("cart");
            account=(Account) session.getAttribute("account");
            order.initOrder(account,cart);
            order.setUsername(account.getUsername());
            order.setCardType(request.getParameter("creditCard"));
            order.setCreditCard(request.getParameter("creditCard"));
            order.setExpiryDate(request.getParameter("expiryDate"));
            order.setBillAddress1(request.getParameter("billAddress1"));
            order.setBillAddress2(request.getParameter("billAddress2"));
            order.setBillCity(request.getParameter("billCity"));
            order.setBillCountry(request.getParameter("billCountry"));
            order.setBillState(request.getParameter("billState"));
            order.setBillToFirstName(request.getParameter("billToFirstName"));
            order.setBillToLastName(request.getParameter("billToLastName"));
            order.setBillZip(request.getParameter("billZip"));
            order.setShipAddress1(request.getParameter("billAddress1"));
            order.setShipAddress2(request.getParameter("billAddress2"));
            order.setShipCity(request.getParameter("billCity"));
            order.setShipCountry(request.getParameter("billCountry"));
            order.setShipState(request.getParameter("billState"));
            order.setShipToFirstName(request.getParameter("billToFirstName"));
            order.setShipToLastName(request.getParameter("billToLastName"));
            order.setShipZip(request.getParameter("billZip"));
            order.setLocale(request.getParameter("billCountry"));
            session.setAttribute("order",order);
            request.getRequestDispatcher(CONFIRM).forward(request,response);
        } else if (order!= null) {
            if(orderService.getOrder(order.getOrderId())==null) {
                orderService.insertOrder(order);
                order.setConfirmed();
            }
            request.setAttribute("message","Thank you, your order has been submitted.");
            request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
        } else {
            request.setAttribute("message","An error occurred processing your order (order was null)");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
