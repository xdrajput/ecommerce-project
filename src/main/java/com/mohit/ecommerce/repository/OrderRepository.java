package com.mohit.ecommerce.repository;
import com.mohit.ecommerce.entity.PurchaseOrder; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<PurchaseOrder,Long>{ Page<PurchaseOrder> findByUserEmailIgnoreCase(String email,Pageable pageable); }
