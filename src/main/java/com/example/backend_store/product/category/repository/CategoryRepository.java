package com.example.backend_store.product.category.repository;

import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


    boolean existsByCategoryName(String categoryName);

    List<Category> findByDeletedFalse();

    List<Category> findCategoryByStoreId(Long store_id);
}
