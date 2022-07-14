package com.kafka.kjh.producer.repository;

import com.kafka.kjh.producer.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
