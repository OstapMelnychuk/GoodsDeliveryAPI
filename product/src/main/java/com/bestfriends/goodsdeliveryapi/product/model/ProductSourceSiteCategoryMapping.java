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

    @ManyToOne(optional = false)
    private ProductCategory category;

    @Column(name = "product_category_url", nullable = false)
    private String url;

    @Column(name = "site_category_name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private ProductSourceSite site;
}
