package com.alethio.service.order.repository;

import com.alethio.service.order.domain.Restock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqRecieveRepository extends JpaRepository<Restock,Long> {
}
