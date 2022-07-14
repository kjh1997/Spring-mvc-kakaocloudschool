package com.kjh.kafka.controller;

import com.kjh.kafka.Domain.Goods;
import com.kjh.kafka.dto.GoodsRequestDto;
import com.kjh.kafka.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    @GetMapping("/getgoods")
    public Goods showGoods(@RequestParam String param) {

        return goodsService.getGoods(param);
    }
}
