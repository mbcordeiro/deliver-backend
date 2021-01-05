package com.matheuscordeiro.deliver.repositories;

import com.matheuscordeiro.deliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
