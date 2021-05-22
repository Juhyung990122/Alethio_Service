package com.alethio.service.order.dto;

import com.alethio.service.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class OrderReturnDto {

    private String orderStatus;
    private OrderCreateDto.contactInfo contactInfo;
    private List<OrderReturnDto.items> items;

    @Getter
    public static class items{
        private Long id;
        private String itemType;
    }

}
