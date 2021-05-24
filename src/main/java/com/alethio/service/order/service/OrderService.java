package com.alethio.service.order.service;

import com.alethio.service.order.domain.Order;
import com.alethio.service.order.dto.OrderRequestDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.repository.OrderRepository;

import com.alethio.service.order.repository.RestockRepository;
import com.alethio.service.product.domain.Item;
import com.alethio.service.product.repository.ItemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final RestockRepository restockRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, RestockRepository restockRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.restockRepository = restockRepository;
    }

    public OrderReturnDto orderRequest(OrderRequestDto order) throws NoSuchElementException {
        OrderRequestDto.items items = order.getItems();

        Item product = itemRepository.findByTypeAndId(items.getItemType(),items.getId());
        try {
            product.setStock(product.getStock() - 1);
            itemRepository.save(product);
            Order saveOrder = orderRepository.save(order.toOrderEntity());
            OrderReturnDto result = saveOrder.toDto();
            return result;
        }
        catch (Exception e){
            restockRepository.save(order.toRestockEntity(product));
            throw new NoSuchElementException("재고가 없습니다.");
        }

    }

}