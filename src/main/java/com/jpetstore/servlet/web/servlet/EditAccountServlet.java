package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.service.AccountService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    //1.定义跳转页面
    private static final String MAIN="/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";
    //2.定义处理请求所需的数据
    private Account account;
    private String username;
    private String password;
    private String rePassword;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private int listOption;
    private int bannerOption;
    private String bannerName;
    //3.是否需要使用业务逻辑层
    private AccountService accountService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = new Account();
        AccountService accountService = new AccountService();
        username = request.getParameter("username");
        password = request.getParameter("password");
        rePassword = request.getParameter("repeatedPassword");
        email = request.getParameter("email");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        status = request.getParameter("status");
        address1 = request.getParameter("address1");
        address2 = request.getParameter("address2");
        city = request.getParameter("city");
        state = request.getParameter("state");
        zip = request.getParameter("zip");
        country = request.getParameter("country");
        phone = request.getParameter("phone");
        /*
        favouriteCategoryId = request.getParameter("favouriteCategoryId");
        languagePreference = request.getParameter("languagePreference");
        listOption = Boolean.getBoolean(request.getParameter("account.listOption"));
        */
        if (!password.equals(rePassword)){
            session.setAttribute("msg","Password is not as same as repeatedPassword");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }else {
            account = (Account) session.getAttribute("account");
            account.setPassword(password);
            account.setEmail(email);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setPhone(phone);
            account.setAddress1(address1);
            account.setAddress2(address2);
            account.setCity(city);
            account.setState(state);
            account.setZip(zip);
            account.setCountry(country);
            accountService.updateAccount(account);
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
    }
}
