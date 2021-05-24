package com.alethio.service.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long p_id;
    private Long id;
    private String name;
    @Min(value = 0)
    private Integer stock;
    private String type;
}
