package com.argus.cms.orderManagement.repositories;

import com.argus.cms.orderManagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
