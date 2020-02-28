package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Cart;
import com.jpetstore.servlet.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class updateByjsServlet extends HttpServlet {
    private Cart cart;
    private String itemId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            int quantity = Integer.parseInt((String) request.getParameter(itemId));
            cart.setQuantityByItemId(itemId, quantity);
            if (quantity < 1) {
                cartItems.remove();
            }
            String res="";
            PrintWriter out=response.getWriter();
            out.print(res);
            out.flush();
            out.close();
        }
    }
}