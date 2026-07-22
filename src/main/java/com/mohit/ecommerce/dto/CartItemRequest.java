package com.mohit.ecommerce.dto;
import jakarta.validation.constraints.*;
public record CartItemRequest(@NotNull Long productId,@NotNull @Positive Integer quantity) {}
