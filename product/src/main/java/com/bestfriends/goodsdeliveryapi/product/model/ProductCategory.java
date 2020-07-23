package com.bestfriends.goodsdeliveryapi.product.model;

import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "setup_product_categories")
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    @Column(name = "product_category_name")
    private String name;

    @ManyToOne
    private ProductCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<ProductCategory> childCategories;
}
