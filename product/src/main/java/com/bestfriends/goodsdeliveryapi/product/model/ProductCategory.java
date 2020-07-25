package com.bestfriends.goodsdeliveryapi.product.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "setup_product_categories")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    @Column(name = "product_category_name", nullable = false)
    private String name;

    @Transient
    private String url;

    @ManyToOne
    private ProductCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<ProductCategory> childCategories;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(url, that.url) &&
            Objects.equals(childCategories, that.childCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, childCategories);
    }
}
