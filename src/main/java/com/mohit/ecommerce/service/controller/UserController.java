package com.mohit.ecommerce.controller;
import com.mohit.ecommerce.dto.UserResponse; import com.mohit.ecommerce.service.UserService; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users") public class UserController{private final UserService s;public UserController(UserService s){this.s=s;}@GetMapping("/me") UserResponse me(Authentication a){return s.me(a.getName());}}
