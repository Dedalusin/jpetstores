package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Product;
import com.jpetstore.servlet.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class findPetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取搜索框输入的内容
        String name=request.getParameter("name");
        //向server层调用相应的业务
        CatalogService catalogService=new CatalogService();
        String res=catalogService.findProductListString(name);
        //返回结果
        PrintWriter out=response.getWriter();
        out.print(res);
        out.flush();
        out.close();
    }
}
