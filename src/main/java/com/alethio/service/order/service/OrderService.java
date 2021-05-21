package com.alethio.service.order.service;

import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.repository.OrderRepository;
import com.alethio.service.product.domain.Cloth;

import com.alethio.service.product.domain.Food;
import com.alethio.service.product.domain.Item;
import com.alethio.service.product.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public String orderRequest(OrderCreateDto order){
        //돌아는 가는데 너무 지저분하다... 정리하기!
        String nowitem = (String) order.getItems().get("itemType");
        Long nowitemid = Long.valueOf(order.getItems().get("id").toString());

        Item requestproduct = itemRepository.findByTypeAndId(nowitem,nowitemid);

        if (requestproduct.getStock() > 0){
            requestproduct.setStock(requestproduct.getStock()-1);
            System.out.println(requestproduct.getStock());
            itemRepository.save(requestproduct);
        }
        return "success";
    }

}
