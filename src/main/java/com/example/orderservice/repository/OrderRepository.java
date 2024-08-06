package com.example.orderservice.repository;

import com.example.orderservice.model.Order;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final DynamoDbTable<Order> orderTable;

    @Autowired
    public OrderRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.orderTable = dynamoDbEnhancedClient.table("Orders", TableSchema.fromBean(Order.class));
    }

    public void saveOrder(Order order) {
        orderTable.putItem(order);
    }

    public Optional<Order> getOrderByNicknameAndId(String nickname, String orderId) {
        return Optional.ofNullable(orderTable.getItem(r -> r.key(k -> k.partitionValue(orderId).sortValue(nickname))));
    }

    public void deleteOrderByNicknameAndId(String nickname, String orderId) {
        orderTable.deleteItem(r -> r.key(k -> k.partitionValue(orderId).sortValue(nickname)));
    }

    public void updateOrder(Order order) {
        orderTable.putItem(order);
    }

    public List<Order> getOrdersByNickname(String nickname) {
        return orderTable.scan().items().stream()
                .filter(order -> order.getNickname().equals(nickname))
                .toList();
    }
}