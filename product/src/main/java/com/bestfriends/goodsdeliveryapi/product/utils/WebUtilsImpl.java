package com.bestfriends.goodsdeliveryapi.product.utils;

import com.bestfriends.goodsdeliveryapi.product.exceptions.ProductException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class WebUtilsImpl implements WebUtils {
    @Override
    public Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ProductException("Can not connect to '%s'. Cause: %s", url, e.getMessage());
        }
    }
}
