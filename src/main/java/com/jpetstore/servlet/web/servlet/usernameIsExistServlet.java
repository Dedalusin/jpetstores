package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class usernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountService accountService=new AccountService();
        String username=request.getParameter("username");
        boolean result=accountService.usernameIsExist(username);
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        if(result){
            out.print("Exist");
        }else{
            out.print("Not Exist");
        }
        out.flush();
        out.close();
    }
}
