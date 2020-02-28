package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class newOrderFormServlet extends HttpServlet {
    private static final String SIGNON="/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String NEWORDERFORM="/WEB-INF/jsp/order/NewOrderForm.jsp";
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        account=(Account) session.getAttribute("account");
        if(account==null)
        {
            session.setAttribute("message","You must sign on before attempting to check out. Please sign on and try checking out again.");
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else {
            request.getRequestDispatcher(NEWORDERFORM).forward(request,response);
        }

    }
}
