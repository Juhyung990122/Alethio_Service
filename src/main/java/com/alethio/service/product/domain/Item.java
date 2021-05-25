package com.alethio.service.product.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private Long id;
    private String name;
    @Min(value = 0)
    private Integer stock;
    private String type;

    public Item(Long id, String name,Integer stock, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stock = stock;
    }
}
