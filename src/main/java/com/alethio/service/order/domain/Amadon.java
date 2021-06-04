package com.alethio.service.order.domain;

import com.alethio.service.product.domain.Item;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
public class Amadon extends Restock {
    // super안에 내용이 넘 긴 것 같다. 간결한 방법 고민해보기.
    public Amadon(Item product, String encrypt){
        super(product.getItemId(), product.getName(), product.getName()+encrypt,100);
    }
}
