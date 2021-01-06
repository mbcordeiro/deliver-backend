package com.matheuscordeiro.deliver.services;

import com.matheuscordeiro.deliver.dto.OrderDTO;
import com.matheuscordeiro.deliver.dto.ProductDTO;
import com.matheuscordeiro.deliver.entities.Order;
import com.matheuscordeiro.deliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findOrderWithProducts();
        return orders.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }
}
