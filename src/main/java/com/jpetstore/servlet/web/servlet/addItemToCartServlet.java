package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Cart;
import com.jpetstore.servlet.domain.Item;
import com.jpetstore.servlet.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class addItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
    private String workingItemId;
    private Cart cart;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId=request.getParameter("workingItemId");
        HttpSession session=request.getSession();
        cart=(Cart) session.getAttribute("cart");
        if(cart==null)
        {
            cart=new Cart();
        }
        if(cart.containsItemId(workingItemId))
        {
            cart.incrementQuantityByItemId(workingItemId);
        }
        else {
            CatalogService catalogService=new CatalogService();
            boolean isItemInstock=catalogService.isItemInStock(workingItemId);
            Item item=catalogService.getItem(workingItemId);
            cart.addItem(item,isItemInstock);
        }
        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
