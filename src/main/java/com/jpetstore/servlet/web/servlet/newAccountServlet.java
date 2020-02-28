package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.service.AccountService;
import com.jpetstore.servlet.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class newAccountServlet extends HttpServlet {
    private static final String MAIN="/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";
    Account account;
    private static String username;
    private static String password;
    private static String firstname;
    private static String lastname;
    private static String email;
    private static String phone;
    private static String address1;
    private static String address2;
    private static String city;
    private static String state;
    private static String zip;
    private static String country;
    private static String repeatedPassword;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        AccountService accountService=new AccountService();
        account = new Account();
        password= request.getParameter("password");
        repeatedPassword=request.getParameter("repeatedPassword");
        if(!repeatedPassword.equals(password))
        {
            session.setAttribute("message","Password is not as same as repeatedPassword");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
        else {
            username=request.getParameter("username");
            account.setUsername(username);
            account.setPassword(password);
            firstname = request.getParameter("firstName");
            account.setFirstName(firstname);
            lastname = request.getParameter("lastName");
            account.setLastName(lastname);
            email = request.getParameter("email");
            account.setEmail(email);
            phone = request.getParameter("phone");
            account.setPhone(phone);
            address1 = request.getParameter("address1");
            account.setAddress1(address1);
            address2 = request.getParameter("address2");
            account.setAddress2(address2);
            city = request.getParameter("city");
            account.setCity(city);
            state = request.getParameter("state");
            account.setState(state);
            zip = request.getParameter("zip");
            account.setZip(zip);
            country = request.getParameter("country");
            account.setCountry(country);
            accountService.insertAccount(account);
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }
}
