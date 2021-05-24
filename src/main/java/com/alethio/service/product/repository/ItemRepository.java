package com.alethio.service.product.repository;

import com.alethio.service.product.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    Item findByTypeAndId(String type,Long id);
}






