package com.bestfriends.goodsdeliveryapi.product.utils;

import org.jsoup.nodes.Document;

/**
 * Class contains method for working with Web resources.
 */
public interface WebUtils {
    /**
     * Method for loading html from given url.
     *
     * @param url of page to load.
     * @return {@link Document} built from loaded html.
     */
    Document getDocument(String url);
}
