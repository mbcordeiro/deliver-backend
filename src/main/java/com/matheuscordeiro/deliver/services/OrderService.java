package com.matheuscordeiro.deliver.services;

import com.matheuscordeiro.deliver.dto.OrderDTO;
import com.matheuscordeiro.deliver.dto.ProductDTO;
import com.matheuscordeiro.deliver.entities.Order;
import com.matheuscordeiro.deliver.entities.Product;
import com.matheuscordeiro.deliver.enums.OrderStatus;
import com.matheuscordeiro.deliver.repositories.OrderRepository;
import com.matheuscordeiro.deliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findOrderWithProducts();
        return orders.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}
