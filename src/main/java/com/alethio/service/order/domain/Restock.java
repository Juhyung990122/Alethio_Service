package com.alethio.service.order.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Restock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restockId;

    private Long id;
    @Column(unique = true)
    private String name;
    private String encryptName;
    private Integer qty;

    public Restock(Long id, String name, String encryptName, Integer qty){
        super();
        this.id = id;
        this.name = name;
        this.encryptName = encryptName;
        this.qty = qty;
    }

}

