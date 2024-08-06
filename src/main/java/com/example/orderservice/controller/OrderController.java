package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order created successfully");
    }

    @GetMapping("/{nickname}/{orderId}")
    public ResponseEntity<Order> getOrderByNicknameAndId(@PathVariable String nickname, @PathVariable String orderId) {
        Optional<Order> order = orderService.getOrderByNicknameAndId(nickname, orderId);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{nickname}/{orderId}")
    public ResponseEntity<String> deleteOrderByNicknameAndId(@PathVariable String nickname, @PathVariable String orderId) {
        orderService.deleteOrderByNicknameAndId(nickname, orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok("Order updated successfully");
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<List<Order>> getOrdersByNickname(@PathVariable String nickname) {
        List<Order> orders = orderService.getOrdersByNickname(nickname);
        return ResponseEntity.ok(orders);
    }
}