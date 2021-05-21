package com.alethio.service.product.domain;

import javax.persistence.*;


@MappedSuperclass
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name="type")
    private String type;
    @Column(name="stock")
    private Integer stock;
    @Column(name="name")
    private String name;

}
