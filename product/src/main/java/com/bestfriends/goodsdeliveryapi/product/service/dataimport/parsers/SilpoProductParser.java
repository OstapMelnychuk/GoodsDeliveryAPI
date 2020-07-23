//package com.bestfriends.goodsdeliveryapi.product.service.dataimport.parsers;
//
//import com.bestfriends.goodsdeliveryapi.product.exceptions.DataImportException;
//import com.bestfriends.goodsdeliveryapi.product.model.Product;
//import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;
//import com.bestfriends.goodsdeliveryapi.product.repositories.ProductSourceSiteRepository;
//import com.bestfriends.goodsdeliveryapi.product.service.dataimport.ProductSiteParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class SilpoProductParser implements ProductSiteParser {
//    final private ProductSourceSiteRepository productSourceSiteRepository;
//
//    @Autowired
//    public SilpoProductParser(ProductSourceSiteRepository productSourceSiteRepository) {
//        this.productSourceSiteRepository = productSourceSiteRepository;
//    }
//
//    @Override
//    public List<Product> parseProducts(ProductSourceSite siteToParse) {
//        return null;
//    }
//
//    @Override
//    public Set<ProductSourceSite> getSupportedSites() {
//        return Set.of(productSourceSiteRepository.findByName("Silpo")
//                .orElseThrow(() -> new DataImportException(null)));
//    }
//}
