package com.alethio.service.order.domain;

import com.alethio.service.product.domain.Item;

public abstract class RestockFactory {
    public abstract Restock create(Item item);
}
