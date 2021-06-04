package com.alethio.service.order.domain;

import com.alethio.service.product.domain.Item;

public class RestockCreate extends RestockFactory{
    @Override
    public Restock create(Item product) {
        // product 타입 유효한지 검사하는 코드 넣을 것.
        // 벤더사 설명 다시 읽고 생각해보기.
        switch (product.getType()){
            case "food":
                return new Cumang(product,"123");

            case "cloth":
                return new Amadon(product,"123");

            default:
                throw new RuntimeException(product.getType().toString()+" is not exist");

        }
    }
}
