package com.niit.bej.order.service.controller;

import com.niit.bej.order.service.model.Item;
import com.niit.bej.order.service.model.Order;
import com.niit.bej.order.service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/insertOrder")
    public ResponseEntity<?> addOrder(HttpServletRequest httpServletRequest, @RequestBody Order order) {
        String email = (String) httpServletRequest.getAttribute("email");
        if (order.getCustomerId().equals(httpServletRequest.getAttribute("email"))) {
            return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Oops there was some error", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getItems/{status}/{email}")
    public ResponseEntity<?> getItems(@PathVariable String status, @PathVariable String email) {
        List<Item> items = orderService.getItems(status, email);
        if (items != null && !items.isEmpty()) {
            return new ResponseEntity<>(orderService.getItems(status, email), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Orders Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addItem/{email}")
    public ResponseEntity<?> addItem(HttpServletRequest httpServletRequest, @PathVariable String email, @RequestBody Item item) {
        String attribute = (String) httpServletRequest.getAttribute("email");
        if (email.equals(attribute)) {
            return new ResponseEntity<>(orderService.addItem(email, item), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("You Are Not Authorized To Add", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/removeItem/{email}")
    public ResponseEntity removeItem(HttpServletRequest httpServletRequest, @PathVariable String email, @RequestBody Item item) {
        String attribute = (String) httpServletRequest.getAttribute("email");
        if (email.equals(attribute)) {
            return new ResponseEntity(orderService.removeItem(email, item), HttpStatus.OK);
        } else
            return new ResponseEntity("You Are Not Authorized To Remove", HttpStatus.UNAUTHORIZED);
    }

}
