package com.alethio.service.order.domain;

import com.alethio.service.product.domain.Item;
import com.alethio.service.product.domain.ItemType;

import java.util.Arrays;
import java.util.List;


public class RestockCreate extends RestockFactory{

    public enum Vendor{
        AmadonProduct(Arrays.asList(ItemType.food,ItemType.drink))
,       CumangProduct(Arrays.asList(ItemType.shoes, ItemType.cloth));

        private List<ItemType> itemList;

        Vendor(List<ItemType> itemList){
            this.itemList = itemList;
        }
    }

    @Override
    public Restock create(Item product) {
        ItemType productType = product.getType();

        // 벤더사 확인
        if (Vendor.AmadonProduct.itemList.contains(productType)) {
            // 물건에 따라서 암호화 코드 다르게 주기.
            switch (productType) {
                case food:
                    return new Cumang(product, "123");

                case drink:
                    return new Cumang(product, "456");

                default:
                    throw new RuntimeException("지원하지 않는 아이템 타입입니다.");

            }
        } else if (Vendor.CumangProduct.itemList.contains(productType)) {
            switch (productType) {
                case cloth:
                    return new Amadon(product, "123");

                case shoes:
                    return new Amadon(product, "456");

                default:
                    throw new RuntimeException("지원하지 않는 아이템 타입입니다.");

            }
        }
        throw new RuntimeException("지원하지 않는 아이템 타입입니다.");
    }
}
