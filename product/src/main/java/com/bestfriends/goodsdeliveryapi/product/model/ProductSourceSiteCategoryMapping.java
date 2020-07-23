package com.bestfriends.goodsdeliveryapi.product.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "setup_product_source_sites_mapping")
@Data
public class ProductSourceSiteCategoryMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_source_site_mapping_id")
    private Long id;

    @ManyToOne
    private ProductCategory category;

    @Column(name = "product_category_url")
    private String url;

    @ManyToOne
    private ProductSourceSite site;
}
