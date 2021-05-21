package com.alethio.service.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("food")
public class Food extends Item{

    private Long id;


}
