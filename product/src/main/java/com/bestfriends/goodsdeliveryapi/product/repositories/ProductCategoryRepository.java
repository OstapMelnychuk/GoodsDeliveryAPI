package com.bestfriends.goodsdeliveryapi.product.repositories;

import com.bestfriends.goodsdeliveryapi.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
