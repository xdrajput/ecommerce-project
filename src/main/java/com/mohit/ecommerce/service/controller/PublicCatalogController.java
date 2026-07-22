package com.mohit.ecommerce.controller;
import com.mohit.ecommerce.dto.*; import com.mohit.ecommerce.service.*; import org.springframework.data.domain.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api") public class PublicCatalogController{
 private final ProductService products;private final CategoryService categories;public PublicCatalogController(ProductService p,CategoryService c){products=p;categories=c;}
 @GetMapping("/products") Page<ProductResponse> all(Pageable p){return products.all(p);}
 @GetMapping("/products/{id}") ProductResponse one(@PathVariable Long id){return products.one(id);}
 @GetMapping("/products/search") Page<ProductResponse> search(@RequestParam String keyword,Pageable p){return products.search(keyword,p);}
 @GetMapping("/products/category/{id}") Page<ProductResponse> byCategory(@PathVariable Long id,Pageable p){return products.byCategory(id,p);}
 @GetMapping("/categories") List<CategoryResponse> categories(){return categories.all();}
 @GetMapping("/categories/{id}") CategoryResponse category(@PathVariable Long id){return categories.one(id);}
}
