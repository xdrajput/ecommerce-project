package com.mohit.ecommerce.dto;
public record AuthResponse(String token,String tokenType,String email,String role) {}
