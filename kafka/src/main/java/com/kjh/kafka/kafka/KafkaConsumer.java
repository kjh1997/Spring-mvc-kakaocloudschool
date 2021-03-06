package com.kjh.kafka.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.kafka.Domain.Goods;
import com.kjh.kafka.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final GoodsService goodsService;

    @KafkaListener(topics = "example-goods-topic")
    public void processMessage(String kafkaMessage) {
        log.info("Kafka Message ====> " + kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Goods entity = goodsService.getGoods((String)map.get("goods"));
        entity.setStock(entity.getStock() - (Integer) map.get("qty"));
        goodsService.saveGoods(entity);

    }
}
