package com.alethio.service.order.dto;

import com.alethio.service.product.domain.ItemType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "OrderReturnDtoBuilder")
public class OrderReturnDto {
    private String contactEmail;
    private String contactName;
    private String mobile;
    private ItemType itemType;
    private Long itemId;
}
