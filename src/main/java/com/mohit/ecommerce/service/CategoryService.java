package com.mohit.ecommerce.service;
import com.mohit.ecommerce.dto.*; import com.mohit.ecommerce.entity.Category; import com.mohit.ecommerce.exception.*; import com.mohit.ecommerce.repository.CategoryRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service
public class CategoryService{
 private final CategoryRepository repo; public CategoryService(CategoryRepository r){repo=r;}
 public CategoryResponse create(CategoryRequest r){if(repo.existsByNameIgnoreCase(r.name()))throw new BusinessException("Category already exists");Category c=new Category();c.setName(r.name());return map(repo.save(c));}
 public List<CategoryResponse> all(){return repo.findAll().stream().map(this::map).toList();}
 public CategoryResponse one(Long id){return map(entity(id));}
 public CategoryResponse update(Long id,CategoryRequest r){Category c=entity(id);repo.findByNameIgnoreCase(r.name()).filter(x->!x.getId().equals(id)).ifPresent(x->{throw new BusinessException("Category already exists");});c.setName(r.name());return map(repo.save(c));}
 public void delete(Long id){repo.delete(entity(id));}
 public Category entity(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found: "+id));}
 private CategoryResponse map(Category c){return new CategoryResponse(c.getId(),c.getName());}
}
