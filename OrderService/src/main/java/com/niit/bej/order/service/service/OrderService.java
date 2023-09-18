package com.niit.bej.order.service.service;

import com.niit.bej.order.service.model.Item;
import com.niit.bej.order.service.model.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    List<Item> getItems(String status, String email);

    boolean removeItem(String email, Item item);
}
