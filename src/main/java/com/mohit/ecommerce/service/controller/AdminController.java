package com.mohit.ecommerce.controller;
import com.mohit.ecommerce.dto.*; import com.mohit.ecommerce.service.*; import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") public class AdminController{
 private final ProductService products;private final CategoryService categories;private final OrderService orders;public AdminController(ProductService p,CategoryService c,OrderService o){products=p;categories=c;orders=o;}
 @PostMapping("/categories") ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(categories.create(r));}
 @PutMapping("/categories/{id}") CategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryRequest r){return categories.update(id,r);}
 @DeleteMapping("/categories/{id}") ResponseEntity<Void> deleteCategory(@PathVariable Long id){categories.delete(id);return ResponseEntity.noContent().build();}
 @PostMapping("/products") ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(products.create(r));}
 @PutMapping("/products/{id}") ProductResponse updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequest r){return products.update(id,r);}
 @DeleteMapping("/products/{id}") ResponseEntity<Void> deleteProduct(@PathVariable Long id){products.delete(id);return ResponseEntity.noContent().build();}
 @GetMapping("/orders") Page<OrderResponse> allOrders(Pageable p){return orders.all(p);}
 @PatchMapping("/orders/{id}/status") OrderResponse status(@PathVariable Long id,@Valid @RequestBody UpdateOrderStatusRequest r){return orders.updateStatus(id,r);}
}
