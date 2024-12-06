package com.argus.cms.orderManagement.repositories;

import com.argus.cms.orderManagement.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
}
