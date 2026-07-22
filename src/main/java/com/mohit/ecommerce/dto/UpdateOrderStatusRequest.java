package com.mohit.ecommerce.dto;
import com.mohit.ecommerce.enums.OrderStatus; import jakarta.validation.constraints.NotNull;
public record UpdateOrderStatusRequest(@NotNull OrderStatus status) {}
