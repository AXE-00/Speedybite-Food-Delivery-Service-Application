package com.niit.bej.order.service.service;

import com.niit.bej.order.service.model.Item;
import com.niit.bej.order.service.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public List<Item> getItems(String status, String email) {
        return null;
    }

    @Override
    public boolean removeItem(String email, Item item) {
        return false;
    }

    @Override
    public boolean addItem(String email, List<Item> items) {
        return false;
    }

    @Override
    public boolean cancelOrder(String email, List<Item> items) {
        return false;
    }

    @Override
    public boolean placeOrder(String email, List<Item> items) {
        return false;
    }
}
