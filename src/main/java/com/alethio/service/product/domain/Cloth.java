package com.alethio.service.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@Table(name="Cloths")
@DiscriminatorValue("cloth")
@NoArgsConstructor
public class Cloth extends Item {

    @Builder
    public Cloth( Long id, String name,Integer stock, String type) {
        super( id, name, stock, type);
    }
}
