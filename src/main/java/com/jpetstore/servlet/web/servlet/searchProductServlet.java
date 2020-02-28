package com.jpetstore.servlet.web.servlet;

import com.jpetstore.servlet.domain.Category;
import com.jpetstore.servlet.domain.Product;
import com.jpetstore.servlet.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class searchProductServlet extends HttpServlet {
    private static final String SEARCH="/WEB-INF/jsp/catalog/SearchProducts.jsp";
    private String keyword;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword=request.getParameter("keyword");
        CatalogService service=new CatalogService();
        List<Product> productList=service.searchProductList(keyword);
        HttpSession session=request.getSession();
        session.setAttribute("productList" , productList);
        request.getRequestDispatcher(SEARCH).forward(request,response);

    }
}
