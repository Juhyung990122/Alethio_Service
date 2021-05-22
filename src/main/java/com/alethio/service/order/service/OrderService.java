package com.alethio.service.order.service;

import com.alethio.service.order.domain.Order;
import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.repository.OrderRepository;

import com.alethio.service.product.domain.Item;
import com.alethio.service.product.repository.ItemRepository;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public OrderReturnDto orderRequest(OrderCreateDto order) throws IOException{
        OrderCreateDto.contactInfo contactInfo = order.getContactInfo();
        OrderCreateDto.items items = order.getItems();

        Item product = itemRepository.findByTypeAndId(items.getItemType(),items.getId());

        if(product.getStock() <= 0){
            // 재고 요청
            throw new IOException("재고가 없습니다.");
        }

        product.setStock(product.getStock()-1);
        itemRepository.save(product);
        // trim 처리 하기
        orderRepository.save(order.toEntity());

        OrderReturnDto result = new OrderReturnDto();
        result.setOrderStatus("success");
        result.setContactInfo(contactInfo);
//        List<Order> orderedItems = orderRepository.findAllByContactEmail(contactInfo.getContactEmail());
//        for(int i = 0; i < orderedItems.size();i++ ){
//            System.out.println(orderedItems.get(i));
//        }
        return result;
    }

}
