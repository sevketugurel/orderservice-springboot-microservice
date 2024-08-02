package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //anotasyon bu sınıfın bir servis bileşeni olduğunu belirtir.
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setDescription(order.getDescription());
                    existingOrder.setAmount(order.getAmount());
                    existingOrder.setUserId(order.getUserId());
                    existingOrder.setProductName(order.getProductName()); // Add this line
                    existingOrder.setQuantity(order.getQuantity()); // Add this line
                    return orderRepository.save(existingOrder);
                }).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
