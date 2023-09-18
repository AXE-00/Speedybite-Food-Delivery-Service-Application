package com.niit.bej.order.service.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String customerId;
    private String billingAddress;
    private List<Item> items;
}
