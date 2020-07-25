package com.bestfriends.goodsdeliveryapi.product.model;

import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "setup_product_source_sites")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "categoryMapping")
public class ProductSourceSite {
    @Id
    @Column(name = "source_site_id")
    private Long id;

    @Column(name = "source_site_name", nullable = false, unique = true)
    private String name;

    @Column(name = "source_site_url", nullable = false, unique = true)
    private String url;

    @OneToMany(mappedBy = "site")
    private List<ProductSourceSiteCategoryMapping> categoryMapping;
}
