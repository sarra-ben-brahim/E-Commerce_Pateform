package com.coding.dojo.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.dojo.Ecommerce.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
