package com.runalb.major.service;

import com.runalb.major.model.Order;
import com.runalb.major.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    public Optional<Order> getOrderById(int id){
        return orderRepository.findById(id);
    }
}
