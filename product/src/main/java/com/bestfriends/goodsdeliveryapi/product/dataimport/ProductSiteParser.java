package com.bestfriends.goodsdeliveryapi.product.dataimport;

import java.util.Set;

/**
 * Interface that is used for parsing product data from different sites.
 */
public interface ProductSiteParser {


    /**
     * Method for getting site names that can be parsed using {@code this} parser.
     *
     * @return {@link Set} with supported site names.
     */
    Set<String> getSupportedSites();
}
