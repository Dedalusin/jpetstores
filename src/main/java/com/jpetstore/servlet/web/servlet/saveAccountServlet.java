package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class saveAccountServlet extends HttpServlet {
    private static final String EDITACCOUNTFORM="/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";
    private Account account;
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
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        password= request.getParameter("password");account.setPassword(password);
        repeatedPassword=request.getParameter("reaeatedPassword");
        if(repeatedPassword!=password)
        {
            session.setAttribute("message","Password is not as same as repeatedPassword");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
        else {
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
            request.getRequestDispatcher(EDITACCOUNTFORM).forward(request, response);
        }

    }
}
