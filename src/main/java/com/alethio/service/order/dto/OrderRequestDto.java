package com.alethio.service.order.dto;

import com.alethio.service.order.domain.Order;
import com.alethio.service.order.domain.Restock;
import com.alethio.service.product.domain.Item;
import lombok.*;


@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private contactInfo contactInfo;
    private items items;
    @Getter
    public static class contactInfo{
        private String contactEmail;
        private String contactName;
        private String mobile;
    }
    @Getter
    @Setter
    public static class items{
        private Long id;
        private String itemType;
    }


    public Order toOrderEntity(){
        return Order.OrderBuilder()
                .contactEmail(contactInfo.contactEmail)
                .contactName(contactInfo.contactName.trim())
                .mobile(contactInfo.mobile.trim())
                .itemId(items.id)
                .itemType(items.itemType)
                .build();

    }

}
