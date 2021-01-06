package com.matheuscordeiro.deliver.services;

import com.matheuscordeiro.deliver.dto.ProductDTO;
import com.matheuscordeiro.deliver.entities.Product;
import com.matheuscordeiro.deliver.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAllByOrderByNameAsc();
        return products.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
    }
}
