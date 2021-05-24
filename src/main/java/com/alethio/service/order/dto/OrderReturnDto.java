package com.alethio.service.order.dto;

import com.alethio.service.order.domain.Order;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "OrderReturnDtoBuilder")
public class OrderReturnDto {
    private String contactEmail;
    private String contactName;
    private String mobile;
    private String itemType;
    private Long itemId;
}
