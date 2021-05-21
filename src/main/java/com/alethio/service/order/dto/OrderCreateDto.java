package com.alethio.service.order.dto;

import com.alethio.service.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@Getter
@NoArgsConstructor
public class OrderCreateDto {
    //해시맵말고 좀더 깔끔한 형태 없나 찾아보기.
    private HashMap contactInfo;
    private HashMap items;

    public Order toEntity(){
        return Order.OrderBuilder()
                .contactEmail(contactInfo.get("contactEmail").toString().trim())
                .contactName((contactInfo.get("contactName").toString().trim()))
                .mobile(contactInfo.get("mobile").toString().trim())
                .itemId((Integer) items.get("id"))
                .itemType((String) items.get("itemType"))
                .build();
    }
}
