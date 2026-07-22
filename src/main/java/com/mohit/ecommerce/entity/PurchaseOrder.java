package com.mohit.ecommerce.entity;
import com.mohit.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="orders")
public class PurchaseOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id",nullable=false) private AppUser user;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private OrderStatus status;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal totalAmount;
 @Column(nullable=false) private LocalDateTime createdAt;
 @Column(nullable=false,length=500) private String shippingAddress;
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true) private List<OrderItem> items=new ArrayList<>();
 public PurchaseOrder(){} public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public AppUser getUser(){return user;} public void setUser(AppUser user){this.user=user;}
 public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus status){this.status=status;}
 public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal totalAmount){this.totalAmount=totalAmount;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
 public String getShippingAddress(){return shippingAddress;} public void setShippingAddress(String shippingAddress){this.shippingAddress=shippingAddress;}
 public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){this.items=items;}
}
