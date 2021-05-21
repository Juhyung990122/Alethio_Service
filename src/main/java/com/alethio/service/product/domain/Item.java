package com.alethio.service.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


//@MappedSuperclass
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@NoArgsConstructor
//@DiscriminatorColumn
@Getter
@Setter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    @Id
    @GeneratedValue
    private Long p_id;

    private String type;
    private Long id;
    private Integer stock;
    private String name;
}
