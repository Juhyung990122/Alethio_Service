package com.alethio.service.order.domain;

import com.alethio.service.order.dto.OrderReturnDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Orders")
@Builder(builderMethodName = "OrderBuilder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String contactEmail;
    private String contactName;
    private String mobile;
    private String itemType;
    private Long itemId;

    public OrderReturnDto toDto(){
        return OrderReturnDto.OrderReturnDtoBuilder()
                .contactEmail(contactEmail)
                .contactName(contactName.trim())
                .mobile(mobile.trim())
                .itemId(itemId)
                .itemType(itemType)
                .build();
    }
}
