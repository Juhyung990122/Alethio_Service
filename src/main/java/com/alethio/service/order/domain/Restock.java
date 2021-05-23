package com.alethio.service.order.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder(builderMethodName = "RestockBuilder")
public class Restock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long id;
    @Column(unique = true)
    private String name;
    private String encryptName;
    private Integer qty;

}
