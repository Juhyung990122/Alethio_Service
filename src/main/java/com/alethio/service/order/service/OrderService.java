package com.alethio.service.order.service;

import com.alethio.service.order.domain.Order;
import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.repository.OrderRepository;

import com.alethio.service.product.domain.Item;
import com.alethio.service.product.repository.ItemRepository;


import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public OrderReturnDto orderRequest(OrderCreateDto order) throws NoSuchElementException {
        OrderCreateDto.contactInfo contactInfo = order.getContactInfo();
        OrderCreateDto.items items = order.getItems();

        Item product = itemRepository.findByTypeAndId(items.getItemType(),items.getId());

        if(product.getStock() <= 0){
            // 재고 요청
            throw new NoSuchElementException("재고가 없습니다.");
        }

        product.setStock(product.getStock()-1);
        itemRepository.save(product);
        Order saveOrder = orderRepository.save(order.toEntity());

        OrderReturnDto result = saveOrder.toDto();
        return result;
    }

}
