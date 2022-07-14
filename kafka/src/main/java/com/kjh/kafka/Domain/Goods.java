package com.kjh.kafka.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Goods {
    @Id
    @GeneratedValue
    @Column(name = "goods_id",unique = true)
    private Long id;
    @Column(nullable = false)
    private Long Stock;
    @Column(nullable = false, unique = true)
    private String name;

}
