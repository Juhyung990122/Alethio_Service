package com.alethio.service.order.controller;

import com.alethio.service.order.dto.OrderRequestDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.service.OrderService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping ("/order")
    public ResponseEntity<?> OrderItem(@RequestBody OrderRequestDto order) {
        try{
            OrderReturnDto result = orderService.orderRequest(order);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateKeyException("이미 입고요청 된 상품입니다.");
        }

    }

}
