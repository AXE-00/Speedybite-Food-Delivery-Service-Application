package com.niit.bej.order.service.repository;

import com.niit.bej.order.service.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
    @Query("{'customerId': ?0}")
    List<Order> findByCustomerId(String email);
}
