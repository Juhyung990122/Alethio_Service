package com.alethio.service.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder(builderMethodName = "RestockBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Restock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restockId;

    private Long id;
    @Column(unique = true)
    private String name;
    private String encryptName;
    private Integer qty;

}
