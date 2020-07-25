package com.bestfriends.goodsdeliveryapi.product.repositories;

import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSourceSiteRepository extends JpaRepository<ProductSourceSite, Long> {
    Set<ProductSourceSite> findByNameIn(Set<String> names);
}
