package com.niit.bej.order.service.service;

import com.niit.bej.order.service.model.Item;
import com.niit.bej.order.service.model.Order;
import com.niit.bej.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
