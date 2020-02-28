package com.jpetstore.servlet.persistance.impl;

import com.jpetstore.servlet.domain.Order;
import com.jpetstore.servlet.persistance.DBUtil;
import com.jpetstore.servlet.persistance.OrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class OrderDAOimpl implements OrderDAO {
    private static final String getOrderString="select BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP,   SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static final String getOrderByUsernameString="SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.USERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE";
    private static final String insertOrderString="INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE,SHIPADDR1,SHIPADDR2, SHIPCITY, SHIPSTATE, SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE,  BILLZIP, BILLCOUNTRY, COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME,  SHIPTOFIRSTNAME, SHIPTOLASTNAME, CREDITCARD, EXPRDATE, CARDTYPE, LOCALE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String insertOrderStatusString="INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";
    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> order=new ArrayList<Order>();
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement pstatement=connection.prepareStatement(getOrderByUsernameString);
            pstatement.setString(1,username);
            ResultSet resultSet=pstatement.executeQuery();
            while(resultSet.next())
            {
                Order order1=new Order();
                order1.setBillAddress1(resultSet.getString(1));
                order1.setBillAddress2(resultSet.getString(2));
                order1.setBillCity(resultSet.getString(3));
                order1.setBillCountry(resultSet.getString(4));
                order1.setBillState(resultSet.getString(5));
                order1.setBillToLastName(resultSet.getString(6));
                order1.setBillToLastName(resultSet.getString(7));
                order1.setBillZip(resultSet.getString(8));
                order1.setShipAddress1(resultSet.getString(9));
                order1.setShipAddress2(resultSet.getString(10));
                order1.setShipCity(resultSet.getString(11));
                order1.setShipCountry(resultSet.getString(12));
                order1.setShipState(resultSet.getString(13));
                order1.setShipToFirstName(resultSet.getString(14));
                order1.setShipToLastName(resultSet.getString(15));
                order1.setShipZip(resultSet.getString(16));
                order1.setCardType(resultSet.getString(17));
                order1.setCourier(resultSet.getString(18));
                order1.setCreditCard(resultSet.getString(19));
                order1.setExpiryDate(resultSet.getString(20));
                order1.setLocale(resultSet.getString(21));
                order1.setOrderDate(resultSet.getDate(22));
                order1.setOrderId(resultSet.getInt(23));
                order1.setTotalPrice(resultSet.getBigDecimal(24));
                order1.setUsername(resultSet.getString(25));
                order1.setStatus(resultSet.getString(26));
                order.add(order1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order=null;
        try
        {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pstatement=connection.prepareStatement(getOrderString);
            pstatement.setInt(1,orderId);
            ResultSet resultSet=pstatement.executeQuery();
            if(resultSet.next())
            {
                order=new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));
                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));
                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));
                order.setCardType(resultSet.getString(16));
                order.setCreditCard(resultSet.getString(17));
                order.setExpiryDate(resultSet.getString(18));
                order.setLocale(resultSet.getString(19));
                order.setOrderDate(resultSet.getDate(20));
                order.setOrderId(resultSet.getInt(21));
                order.setTotalPrice(resultSet.getBigDecimal(22));
                order.setUsername(resultSet.getString(23));
                order.setStatus(resultSet.getString(24));
            }
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pstatement);
                DBUtil.closeConnection(connection);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void insertOrder(Order order) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(insertOrderString);
            pStatement.setInt(1,order.getOrderId());
            pStatement.setString(2,order.getUsername());
            pStatement.setDate(3,sqlDate );
            pStatement.setString(4,order.getShipAddress1());
            pStatement.setString(5,order.getShipAddress2());
            pStatement.setString(6,order.getShipCity());
            pStatement.setString(7,order.getShipState());
            pStatement.setString(8,order.getShipZip());
            pStatement.setString(9,order.getShipCountry());
            pStatement.setString(10,order.getBillAddress1());
            pStatement.setString(11,order.getBillAddress2());
            pStatement.setString(12,order.getBillCity());
            pStatement.setString(13,order.getBillState());
            pStatement.setString(14,order.getBillZip());
            pStatement.setString(15,order.getBillCountry());
            pStatement.setString(16,order.getCourier());
            pStatement.setBigDecimal(17,order.getTotalPrice());
            pStatement.setString(18,order.getBillToFirstName());
            pStatement.setString(19,order.getBillToLastName());
            pStatement.setString(20,order.getShipToFirstName());
            pStatement.setString(21,order.getShipToLastName());
            pStatement.setString(22,order.getCreditCard());
            pStatement.setString(23,order.getExpiryDate());
            pStatement.setString(24,order.getCardType());
            pStatement.setString(25,order.getLocale());
            pStatement.executeUpdate();
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());
            Connection connection= DBUtil.getConnection();
            PreparedStatement pstatement=connection.prepareStatement(insertOrderStatusString);
            pstatement.setInt(1,order.getOrderId());
            pstatement.setInt(2,order.getOrderId());
            pstatement.setDate(3,sqlDate);
            pstatement.setString(4,order.getStatus());
            pstatement.executeUpdate();
            DBUtil.closePreparedStatement(pstatement);
            DBUtil.closeConnection(connection);
            DBUtil.closeConnection(connection);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
