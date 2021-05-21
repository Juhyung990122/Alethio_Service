package com.alethio.service.order.controller;

import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping ("/order")
    public String OrderItem(@RequestBody OrderCreateDto order) {
        System.out.println(order.getContactInfo().get("contactEmail"));
        String result = orderService.orderRequest(order);
        return result;
    }

}
