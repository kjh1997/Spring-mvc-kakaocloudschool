package com.kjh.kafka.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Goods {
    @Column(name = "Category_id",unique = true)
    private Long id;
    @Column(nullable = false)
    private Long Stock;
    @Column(nullable = false, unique = true)
    private String name;

}
