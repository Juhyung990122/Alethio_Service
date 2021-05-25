package com.alethio.service.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="Foods")
@NoArgsConstructor
@DiscriminatorValue("food")
public class Food extends Item {
    @Builder
    public Food( Long id, String name,Integer stock, String type) {
        super( id, name, stock, type);
    }
}
