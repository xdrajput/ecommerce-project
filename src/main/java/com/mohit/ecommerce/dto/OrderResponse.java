package com.mohit.ecommerce.dto;
import com.mohit.ecommerce.enums.OrderStatus; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.List;
public record OrderResponse(Long id,String customerEmail,OrderStatus status,BigDecimal totalAmount,LocalDateTime createdAt,String shippingAddress,List<OrderItemResponse> items) {}
