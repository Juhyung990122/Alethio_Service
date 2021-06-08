package com.alethio.service.order.domain;

import com.alethio.service.product.domain.Item;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
public class Amadon extends Restock {
    public Amadon(Item product, String encrypt){
        super(product.getItemId(), product.getName(), product.getName()+encrypt,100);
    }
}
