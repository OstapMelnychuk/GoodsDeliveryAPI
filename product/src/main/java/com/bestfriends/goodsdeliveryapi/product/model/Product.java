package com.bestfriends.goodsdeliveryapi.product.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "supplier_id", nullable = false)
    private Long supplier;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency_id")
    private BigDecimal currency;

    @ManyToOne(optional = false)
    private MeasurementUnit measurementUnit;

    @ManyToMany
    @JoinTable(
        name = "products_setup_product_categories_lnk",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "product_category_id"))
    private Set<ProductCategory> categories;
}
