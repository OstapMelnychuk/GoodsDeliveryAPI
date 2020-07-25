package com.bestfriends.goodsdeliveryapi.product.service.dataimport.category.parsers;

import com.bestfriends.goodsdeliveryapi.product.model.ProductCategory;
import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;
import com.bestfriends.goodsdeliveryapi.product.repositories.ProductSourceSiteRepository;
import com.bestfriends.goodsdeliveryapi.product.service.dataimport.SiteCategoryParser;
import com.bestfriends.goodsdeliveryapi.product.utils.WebUtils;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RukavychkaCategoryParser implements SiteCategoryParser {
    private final ProductSourceSiteRepository siteRepository;
    private final WebUtils webUtils;

    @Autowired
    public RukavychkaCategoryParser(ProductSourceSiteRepository siteRepository, WebUtils webUtils) {
        this.siteRepository = siteRepository;
        this.webUtils = webUtils;
    }

    @Override
    public Set<ProductCategory> parseCategories(ProductSourceSite site) {
        final String categoriesClassName = "row no-gutters";

        Document document = webUtils.getDocument(site.getUrl());
        Element categoriesElement = document.getElementsByClass(categoriesClassName)
            .get(0);

        return parseParentProductCategoriesFromElement(categoriesElement);
    }

    @Override
    public Set<ProductSourceSite> supports() {
        return siteRepository.findByNameIn(getSupportedSiteNames());
    }

    /**
     * @return names of supported sites to parse categories.
     */
    public Set<String> getSupportedSiteNames() {
        return Set.of("Rukavychka");
    }

    private Set<ProductCategory> parseParentProductCategoriesFromElement(Element element) {
        return element.children().stream()
            .map(this::parseParentProductCategoryFromElement)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private ProductCategory parseParentProductCategoryFromElement(Element element) {
        final String categoryNameClass = "fm-category-wall-item-title";
        final String childCategoriesClassName = "list-unstyled";

        String categoryUrl = element.getElementsByClass(categoryNameClass)
            .get(0)
            .attr("href");
        String categoryName = element.getElementsByClass(categoryNameClass)
            .get(0)
            .text();

        ProductCategory parentCategory = ProductCategory.builder()
            .name(categoryName)
            .url(categoryUrl)
            .build();

        parentCategory.setChildCategories(parseChildCategoriesFromElement(
            element.getElementsByClass(childCategoriesClassName)
                .get(0),
            parentCategory));

        return parentCategory;
    }

    private Set<ProductCategory> parseChildCategoriesFromElement(Element element, ProductCategory parentCategory) {
        return element.children().stream()
            .map(e -> ProductCategory.builder()
                .url(e.child(0).attr("href"))
                .name(e.child(0).text())
                .parentCategory(parentCategory)
                .build())
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
