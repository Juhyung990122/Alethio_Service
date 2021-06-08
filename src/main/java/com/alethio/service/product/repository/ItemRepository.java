package com.alethio.service.product.repository;

import com.alethio.service.product.domain.Item;
import com.alethio.service.product.domain.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    Item findByTypeAndId(ItemType type, Long id);
}






