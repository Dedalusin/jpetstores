package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class signonServlet extends HttpServlet {
    private static final String SIGNON="/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String EDITACCOUNTFORM="/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String MAIN="/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";
    private Account account;
    private String username;
    private String password;
    private String checkcode;
    private String checkcodef;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        username=request.getParameter("username");
        password=request.getParameter("password");
        checkcode=request.getParameter("checkcode");
        checkcodef=request.getParameter("content");
        AccountService accountService=new AccountService();
        account = accountService.getAccount(username, password);
        if (account == null) {
            session.setAttribute("message","Invalid username or password.  Signon failed.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        } else if (checkcode.equals(checkcodef)){
            session.setAttribute("account",account);
            request.getRequestDispatcher(MAIN).forward(request,response);}
        else {
            session.setAttribute("message","Invalid verification code.  Signon failed.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
