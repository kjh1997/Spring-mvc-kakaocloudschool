package com.kjh.kafka.repository;

import com.kjh.kafka.Domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Goods findByName(String name);
}
