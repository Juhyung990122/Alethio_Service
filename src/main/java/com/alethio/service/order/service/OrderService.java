package com.alethio.service.order.service;

import com.alethio.service.order.domain.Order;
import com.alethio.service.order.dto.OrderRequestDto;
import com.alethio.service.order.dto.OrderReturnDto;
import com.alethio.service.order.repository.OrderRepository;

import com.alethio.service.order.repository.RestockRepository;
import com.alethio.service.product.domain.Item;
import com.alethio.service.product.repository.ItemRepository;

import org.h2.message.DbException;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.RollbackException;
import javax.xml.crypto.Data;
import java.sql.SQLException;
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

    public OrderReturnDto orderRequest(OrderRequestDto order) throws NoSuchElementException{
        OrderRequestDto.items items = order.getItems();

        Item product = itemRepository.findByTypeAndId(items.getItemType(),items.getId());
        try {

            // 재고 및 입고요청
            product.setStock(product.getStock() - 1);

            if (product.getStock() <= 9){
                if (restockRepository.findByName(product.getName()) == null){
                    restockRepository.save(order.toRestockEntity(product));
                }
            }

            itemRepository.save(product);

            // 주문
            Order savedOrder = orderRepository.save(order.toOrderEntity());
            OrderReturnDto result = savedOrder.toDto();
            return result;

        } catch (NullPointerException e){
            // 상품 없음
            throw new NoSuchElementException("등록되지 않은 상품입니다.");

        } catch (TransactionSystemException e) {
            // 재고 부족
            throw new NoSuchElementException("재고가 없습니다.");

        }

    }
}