package com.bestfriends.goodsdeliveryapi.product.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setup_product_source_sites")
@Data
public class ProductSourceSite {
    @Id
    @Column(name = "source_site_id")
    private Long id;

    @Column(name = "source_site_name", nullable = false, unique = true)
    private String name;

    @Column(name = "source_site_url", nullable = false, unique = true)
    private String url;
}
