package com.tmall.dao;

import com.tmall.pojo.Order;
import com.tmall.pojo.OrderItem;
import com.tmall.pojo.Product;
import com.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}