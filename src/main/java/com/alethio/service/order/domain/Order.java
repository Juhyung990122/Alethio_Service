package com.alethio.service.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Orders")
@Builder(builderMethodName = "OrderBuilder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactEmail;
    private String contactName;
    private String mobile;
    private String itemType;
    private Integer itemId;
}
