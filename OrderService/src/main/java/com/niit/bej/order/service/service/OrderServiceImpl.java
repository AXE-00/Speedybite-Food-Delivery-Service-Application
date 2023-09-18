package com.niit.bej.order.service.service;

import com.niit.bej.order.service.model.Item;
import com.niit.bej.order.service.model.Order;
import com.niit.bej.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        Item receivedItem = order.getItems().get(0);
        List<Order> orders = orderRepository.findByCustomerId(order.getCustomerId());
        if (orders.isEmpty()) { //If there is no existing order for this customer
            return orderRepository.save(order); //save a new order
        } else {
            Order existingOrder = orders.get(0);
            boolean itemExists = false;
            for (Item item : existingOrder.getItems()) {
                if (item.getItemName().equals(receivedItem.getItemName()) && item.getStatus().equals(receivedItem.getStatus())) {
                    item.setCount(item.getCount() + 1); //update the count of the existing item
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) { //If the item does not exist in the existing order
                existingOrder.getItems().add(receivedItem); //add the new item to the existing item
            }
            return orderRepository.save(existingOrder); //update the existing order
        }
    }

    @Override
    public List<Item> getItems(String status, String email) {
        List<Order> orders = orderRepository.findByCustomerId(email);
        List<Item> items = new ArrayList<>();
        for (Order order : orders) {
            for (Item item : order.getItems()) {
                if (item.getStatus().equals(status)) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    @Override
    public boolean removeItem(String email, Item item) {
        List<Order> orders = orderRepository.findByCustomerId(email);
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
                Item existingItem = order.getItems().stream().filter(item1 -> item1.getItemName().equals(item.getItemName()) && item1.getStatus().equals(item.getStatus())).findFirst().orElse(null);
                if (existingItem != null) {
                    int count = existingItem.getCount();
                    if (count > 1) {
                        count--;
                        existingItem.setCount(count);
                    } else if (count == 1) {
                        order.getItems().remove(existingItem);
                    }
                    orderRepository.save(order);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addItem(String email, Item items) {
        List<Order> orders = orderRepository.findByCustomerId(email);
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
                Item existingOrder = order.getItems().stream().filter(item -> item.getItemName().equals(items.getItemName()) && item.getStatus().equals(item.getStatus())).findFirst().orElse(null);
                if (existingOrder != null) {
                    int count = existingOrder.getCount() + 1;
                    existingOrder.setCount(count);
                }
                orderRepository.save(order);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelOrder(String email, List<Item> items) {
        List<Order> orders = orderRepository.findByCustomerId(email);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                boolean orderUpdated = false;
                for (Item item : order.getItems()) {
                    if (item.getStatus().equals("ordered") && items.contains(item)) {
                        item.setStatus("cancelled");
                        orderUpdated = true;
                    }
                }
                if (orderUpdated) {
                    orderRepository.save(order);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean placeOrder(String email, List<Item> items) {
        List<Order> orders = orderRepository.findByCustomerId(email);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                boolean orderUpdated = false;
                for (Item item : order.getItems()) {
                    if (item.getStatus().equals("inCart") && items.contains(item)) {
                        item.setStatus("ordered");
                        orderUpdated = true;
                    }
                }
                if (orderUpdated) {
                    orderRepository.save(order);
                }
            }
            return true;
        }
        return false;
    }
}
