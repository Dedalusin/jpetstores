package com.jpetstore.servlet.persistance.impl;

import com.jpetstore.servlet.domain.Sequence;
import com.jpetstore.servlet.persistance.DBUtil;
import com.jpetstore.servlet.persistance.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOimpl implements SequenceDAO {
    private static final String getSequenceString="SELECT name, nextid FROM SEQUENCE WHERE NAME =?";
    private static final String updateSequenceString="UPDATE SEQUENCE SET NEXTID = ? WHERE NAME =?";
    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence sequence1=null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pstatement=connection.prepareStatement(getSequenceString);
            pstatement.setString(1,sequence.getName());
            ResultSet resultSet=pstatement.executeQuery();
            if(resultSet.next())
            {
                sequence1=new Sequence();
                sequence1.setName(resultSet.getString(1));
                sequence1.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
             e.printStackTrace();
        }

        return sequence1;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement pstatement=connection.prepareStatement(updateSequenceString);
            pstatement.setInt(1,sequence.getNextId());
            pstatement.setString(2,sequence.getName());
            pstatement.executeUpdate();
            DBUtil.closePreparedStatement(pstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
