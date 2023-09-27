package com.niit.bej.payment.service.controller;

import com.niit.bej.payment.service.model.PaymentOrder;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @PostMapping("/createOrder")
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody PaymentOrder paymentOrder) {
        com.razorpay.Order order;
        try {
            var client = new RazorpayClient("rzp_test_Nv2JwuXBqlVpsN", "GXNVuxdg7aYApF9FxOWnqrbn");
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", paymentOrder.getOrderAmount() * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_12345");
            System.out.println(orderRequest);
            order = client.orders.create(orderRequest);
        } catch (RazorpayException exception) {
            throw new RuntimeException(exception);
        }
        return new ResponseEntity<>(order.toString(), HttpStatus.CREATED);
    }
}
