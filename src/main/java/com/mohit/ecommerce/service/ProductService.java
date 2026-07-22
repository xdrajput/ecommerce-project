package com.mohit.ecommerce.service;
import com.mohit.ecommerce.dto.*; import com.mohit.ecommerce.entity.*; import com.mohit.ecommerce.exception.ResourceNotFoundException; import com.mohit.ecommerce.repository.ProductRepository; import org.springframework.data.domain.*; import org.springframework.stereotype.Service;
@Service
public class ProductService{
 private final ProductRepository repo; private final CategoryService categories; public ProductService(ProductRepository r,CategoryService c){repo=r;categories=c;}
 public ProductResponse create(ProductRequest r){Product p=new Product();copy(r,p);return map(repo.save(p));}
 public ProductResponse one(Long id){return map(entity(id));}
 public Page<ProductResponse> all(Pageable p){return repo.findAll(p).map(this::map);}
 public Page<ProductResponse> search(String q,Pageable p){return repo.findByNameContainingIgnoreCase(q,p).map(this::map);}
 public Page<ProductResponse> byCategory(Long id,Pageable p){categories.entity(id);return repo.findByCategoryId(id,p).map(this::map);}
 public ProductResponse update(Long id,ProductRequest r){Product p=entity(id);copy(r,p);return map(repo.save(p));}
 public void delete(Long id){repo.delete(entity(id));}
 public Product entity(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: "+id));}
 private void copy(ProductRequest r,Product p){p.setName(r.name());p.setDescription(r.description());p.setPrice(r.price());p.setStock(r.stock());p.setImageUrl(r.imageUrl());p.setCategory(categories.entity(r.categoryId()));}
 public ProductResponse map(Product p){return new ProductResponse(p.getId(),p.getName(),p.getDescription(),p.getPrice(),p.getStock(),p.getImageUrl(),p.getCategory().getId(),p.getCategory().getName());}
}
