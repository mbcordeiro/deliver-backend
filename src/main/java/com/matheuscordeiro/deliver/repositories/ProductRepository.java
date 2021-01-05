package com.matheuscordeiro.deliver.repositories;

import com.matheuscordeiro.deliver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
