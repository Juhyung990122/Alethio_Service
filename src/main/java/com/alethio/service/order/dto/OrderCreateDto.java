package com.alethio.service.order.dto;

import com.alethio.service.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor
public class OrderCreateDto {

    private contactInfo contactInfo;
    private items items;
    //해시맵말고 좀더 깔끔한 형태 없나 찾아보기.
    @Getter
    public static class contactInfo{
        private String contactEmail;
        private String contactName;
        private String mobile;
    }
    @Getter
    public static class items{
        private Long id;
        private String itemType;
    }

    public Order toEntity(){
        return Order.OrderBuilder()
                .contactEmail(contactInfo.contactEmail)
                .contactName(contactInfo.contactName.trim())
                .mobile(contactInfo.mobile.trim())
                .id(items.id)
                .itemType(items.itemType)
                .build();

    }
}
