package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        orderRepository.saveOrder(order);
    }

    public Optional<Order> getOrderByNicknameAndId(String nickname, String orderId) {
        return orderRepository.getOrderByNicknameAndId(nickname, orderId);
    }

    public void deleteOrderByNicknameAndId(String nickname, String orderId) {
        orderRepository.deleteOrderByNicknameAndId(nickname, orderId);
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    public List<Order> getOrdersByNickname(String nickname) {
        return orderRepository.getOrdersByNickname(nickname);
    }
}