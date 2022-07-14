package com.kjh.kafka.service;

import com.kjh.kafka.Domain.Goods;
import com.kjh.kafka.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Goods getGoods(String name) {
        return goodsRepository.findByName(name);
    }

}
