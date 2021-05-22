package com.alethio.service.order.controller;

import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping ("/order")
    public OrderReturnDto OrderItem(@RequestBody OrderCreateDto order) throws IOException {
        //System.out.println(order.getContactInfo().get("contactEmail"));
        OrderReturnDto result = orderService.orderRequest(order);
        return result;
    }

}
