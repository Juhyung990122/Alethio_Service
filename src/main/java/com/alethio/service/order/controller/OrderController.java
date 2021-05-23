package com.alethio.service.order.controller;

import com.alethio.service.order.dto.OrderCreateDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping ("/order")
    public ResponseEntity<?> OrderItem(@RequestBody OrderCreateDto order) throws IOException {
        OrderReturnDto result = orderService.orderRequest(order);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
