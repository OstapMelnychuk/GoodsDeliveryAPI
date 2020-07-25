package com.bestfriends.goodsdeliveryapi.product.service.dataimport;

import com.bestfriends.goodsdeliveryapi.product.model.ProductCategory;
import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;
import java.util.Set;

/**
 * Interface used for parsing categories from site.
 */
public interface SiteCategoryParser {
    /**
     * Method for parsing categories from given site.
     *
     * @param site site to parse categories.
     * @return site categories.
     */
    Set<ProductCategory> parseCategories(ProductSourceSite site);

    /**
     * @return list of supported {@link ProductSourceSite}.
     */
    Set<ProductSourceSite> supports();
}
