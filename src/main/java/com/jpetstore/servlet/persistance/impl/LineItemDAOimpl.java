package com.jpetstore.servlet.persistance.impl;

import com.jpetstore.servlet.domain.LineItem;
import com.jpetstore.servlet.persistance.DBUtil;
import com.jpetstore.servlet.persistance.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class LineItemDAOimpl implements LineItemDAO {
    private static final String getLineItemsByOrderIdString="SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String insertLineItemString="INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItems=new ArrayList<>();
        try
        {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(getLineItemsByOrderIdString);
            pStatement.setInt(1,orderId);
            ResultSet resultSet=pStatement.executeQuery();
            while (resultSet.next())
            {
                LineItem lineItem=new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItems.add(lineItem);
            }
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);

        }catch (Exception e)
        {
             e.printStackTrace();
        }

        return lineItems;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try
        {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(insertLineItemString);
            pStatement.setInt(1,lineItem.getOrderId());
            pStatement.setInt(2,lineItem.getLineNumber());
            pStatement.setString(3,lineItem.getItemId());
            pStatement.setInt(4,lineItem.getQuantity());
            pStatement.setBigDecimal(5,lineItem.getUnitPrice());
            pStatement.executeUpdate();
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
