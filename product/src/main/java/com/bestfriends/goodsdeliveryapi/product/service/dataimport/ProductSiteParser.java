package com.bestfriends.goodsdeliveryapi.product.service.dataimport;

import com.bestfriends.goodsdeliveryapi.product.model.Product;
import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;

import java.util.List;
import java.util.Set;

/**
 * Interface that is used for parsing product data from different sites.
 */
public interface ProductSiteParser {
    /**
     * Method for parsing products from given site.
     *
     * @param siteToParse product source site.
     * @return {@link List} of {@link Product} from source site.
     */
    List<Product> parseProducts(ProductSourceSite siteToParse);

    /**
     * Method for getting site names that can be parsed using {@code this} parser.
     *
     * @return {@link Set} with supported site names.
     */
    Set<ProductSourceSite> getSupportedSites();
}
