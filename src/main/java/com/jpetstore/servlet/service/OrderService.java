package com.jpetstore.servlet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpetstore.servlet.domain.Item;
import com.jpetstore.servlet.domain.LineItem;
import com.jpetstore.servlet.domain.Order;
import com.jpetstore.servlet.domain.Sequence;
import com.jpetstore.servlet.persistance.impl.ItemDAOimpl;
import com.jpetstore.servlet.persistance.impl.LineItemDAOimpl;
import com.jpetstore.servlet.persistance.impl.OrderDAOimpl;
import com.jpetstore.servlet.persistance.impl.SequenceDAOimpl;


public class OrderService{


  private ItemDAOimpl itemDAOimpl=new ItemDAOimpl();

  private OrderDAOimpl orderDAOimpl=new OrderDAOimpl();

  private SequenceDAOimpl sequenceDAOimpl=new SequenceDAOimpl();

  private LineItemDAOimpl lineItemDAOimpl=new LineItemDAOimpl();


  public void insertOrder(Order order) {
    order.setOrderId(getNextId("ordernum"));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = lineItem.getQuantity();
      Map<String, Object> param = new HashMap<String, Object>(2);
      param.put("itemId", itemId);
      param.put("increment", increment);
      itemDAOimpl.updateInventoryQuantity(param);
    }

    orderDAOimpl.insertOrder(order);
    orderDAOimpl.insertOrderStatus(order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemDAOimpl.insertLineItem(lineItem);
    }
  }


  public Order getOrder(int orderId) {
    Order order = orderDAOimpl.getOrder(orderId);
    if(order!=null) {
      order.setLineItems(lineItemDAOimpl.getLineItemsByOrderId(orderId));

      for (int i = 0; i < order.getLineItems().size(); i++) {
        LineItem lineItem = (LineItem) order.getLineItems().get(i);
        Item item = itemDAOimpl.getItem(lineItem.getItemId());
        item.setQuantity(itemDAOimpl.getInventoryQuantity(lineItem.getItemId()));
        lineItem.setItem(item);
      }
    }
    return order;
  }

  public List<Order> getOrdersByUsername(String username) {
    return orderDAOimpl.getOrdersByUsername(username);
  }

  public int getNextId(String name) {
    Sequence sequence = new Sequence(name, -1);
    sequence = (Sequence) sequenceDAOimpl.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
          + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    sequenceDAOimpl.updateSequence(parameterObject);
    return sequence.getNextId();
  }
  public List<Order> getListOrder(String username){
    return orderDAOimpl.getOrdersByUsername(username);
  }

}
