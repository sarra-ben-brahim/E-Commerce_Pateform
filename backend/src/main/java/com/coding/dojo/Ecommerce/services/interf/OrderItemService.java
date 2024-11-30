package com.coding.dojo.Ecommerce.services.interf;


import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import com.coding.dojo.Ecommerce.dto.OrderRequest;
import com.coding.dojo.Ecommerce.dto.Response;
import com.coding.dojo.Ecommerce.enumerations.OrderStatus;

public interface OrderItemService {
    Response placeOrder(OrderRequest orderRequest);
    Response updateOrderItemStatus(Long orderItemId, String status);
    Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable);
}