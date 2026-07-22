package com.mohit.ecommerce.repository;
import com.mohit.ecommerce.entity.Product; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long>{ Page<Product> findByNameContainingIgnoreCase(String keyword,Pageable pageable); Page<Product> findByCategoryId(Long categoryId,Pageable pageable); }
