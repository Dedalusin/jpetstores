package com.jpetstore.servlet.persistance.impl;

import com.jpetstore.servlet.domain.Product;
import com.jpetstore.servlet.persistance.DBUtil;
import com.jpetstore.servlet.persistance.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ProductDAOimpl implements ProductDAO {
    private static String getProductListByCategoryString="SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY =?";
    private static String getProductString="SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID =?";
    private static String searchProductListString="SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> Products=new ArrayList<Product>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(getProductListByCategoryString);
            pStatement.setString(1,categoryId);
            ResultSet resultSet=pStatement.executeQuery();
            while (resultSet.next())
            {
                Product Product=new Product();
                Product.setProductId(resultSet.getString(1));
                Product.setName(resultSet.getString(2));
                Product.setDescription(resultSet.getString(3));
                Product.setCategoryId(resultSet.getString(4));
                Products.add(Product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return Products;
    }

    @Override
    public Product getProduct(String productId)
    {
        Product product=null;
        try {
            Connection connection=DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(getProductString);
            pStatement.setString(1,productId);
            ResultSet resultSet=pStatement.executeQuery();
            if(resultSet.next())
            {
                product=new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> ProductList=new ArrayList<Product>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(searchProductListString);
            pStatement.setString(1,keywords);
            ResultSet resultSet=pStatement.executeQuery();
            while (resultSet.next())
            {
                Product Product=new Product();
                Product.setProductId(resultSet.getString(1));
                Product.setName(resultSet.getString(2));
                Product.setDescription(resultSet.getString(3));
                Product.setCategoryId(resultSet.getString(4));
                ProductList.add(Product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ProductList;
    }
}
