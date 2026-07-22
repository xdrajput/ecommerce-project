package com.mohit.ecommerce.repository;
import com.mohit.ecommerce.entity.Cart; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface CartRepository extends JpaRepository<Cart,Long>{ Optional<Cart> findByUserEmailIgnoreCase(String email); }
