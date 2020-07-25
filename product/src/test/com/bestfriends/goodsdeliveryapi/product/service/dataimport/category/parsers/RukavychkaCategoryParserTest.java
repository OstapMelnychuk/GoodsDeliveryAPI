package com.bestfriends.goodsdeliveryapi.product.service.dataimport.category.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.bestfriends.goodsdeliveryapi.product.model.ProductCategory;
import com.bestfriends.goodsdeliveryapi.product.model.ProductSourceSite;
import com.bestfriends.goodsdeliveryapi.product.repositories.ProductSourceSiteRepository;
import com.bestfriends.goodsdeliveryapi.product.utils.WebUtils;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( {MockitoExtension.class})
class RukavychkaCategoryParserTest {
    private static final String TEST_WEB_PAGE_FILE_PATH = "src\\test\\resources\\html\\Rukavychka_category_page.html"
        .replace("\\", File.separator);
    private static final String SUPPORTED_SITE_NAME = "Rukavychka";
    private static final String SUPPORTED_SITE_URL = "https://market.rukavychka.ua";

    @Mock
    private ProductSourceSiteRepository productSourceSiteRepository;
    @Mock
    private WebUtils webUtils;

    String webPageHtml;
    private RukavychkaCategoryParser parser;
    private ProductSourceSite sourceSite;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        webPageHtml = IOUtils.toString(new FileInputStream(TEST_WEB_PAGE_FILE_PATH));

        this.parser = new RukavychkaCategoryParser(productSourceSiteRepository, webUtils);
        this.sourceSite = ProductSourceSite.builder()
            .name(SUPPORTED_SITE_NAME)
            .url(SUPPORTED_SITE_URL)
            .build();
    }

    @Test
    void testParseCategories() {
        when(webUtils.getDocument(SUPPORTED_SITE_URL))
            .thenReturn(Jsoup.parse(webPageHtml));

        Set<ProductCategory> actualCategories = parser.parseCategories(sourceSite);
        Set<ProductCategory> expectedCategories = expectedProductCategories();

        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    void testSupports() {
        Set<String> supportedSiteNames = Set.of(SUPPORTED_SITE_NAME);
        ProductSourceSite expectedSite = ProductSourceSite.builder()
            .id(1L)
            .name(SUPPORTED_SITE_NAME)
            .url(SUPPORTED_SITE_URL)
            .build();

        when(productSourceSiteRepository.findByNameIn(supportedSiteNames))
            .thenReturn(Set.of(expectedSite));

        assertEquals(Set.of(expectedSite), parser.supports());
    }

    private Set<ProductCategory> expectedProductCategories() {
        ProductCategory parentCategory1 = ProductCategory.builder()
            .name("М’ясо, риба, птиця")
            .url("https://market.rukavychka.ua/mjaso-riba-pticja/")
            .build();
        ProductCategory parentCategory2 = ProductCategory.builder()
            .name("Молочні продукти та яйця")
            .url("https://market.rukavychka.ua/molochni-produkti-ta-jajcja/")
            .build();

        ProductCategory childCategory1 = ProductCategory.builder()
            .name("Ікра")
            .url("https://market.rukavychka.ua/ikra/")
            .parentCategory(parentCategory1)
            .build();
        ProductCategory childCategory2 = ProductCategory.builder()
            .name("Ковбаса і сосиски")
            .url("https://market.rukavychka.ua/kovbasa-i-sosiski/")
            .parentCategory(parentCategory1)
            .build();
        ProductCategory childCategory3 = ProductCategory.builder()
            .name("М’ясо і напівфабрикати")
            .url("https://market.rukavychka.ua/mjaso-i-napivfabrikati/")
            .parentCategory(parentCategory1)
            .build();
        ProductCategory childCategory4 = ProductCategory.builder()
            .name("Риба і морепродукти")
            .url("https://market.rukavychka.ua/riba-i-moreprodukti/")
            .parentCategory(parentCategory1)
            .build();

        ProductCategory childCategory5 = ProductCategory.builder()
            .name("Вершки")
            .url("https://market.rukavychka.ua/vershki/")
            .parentCategory(parentCategory2)
            .build();
        ProductCategory childCategory6 = ProductCategory.builder()
            .name("Згущене молоко")
            .url("https://market.rukavychka.ua/zguschene-moloko/")
            .parentCategory(parentCategory2)
            .build();

        parentCategory1.setChildCategories(Stream.of(
            childCategory1,
            childCategory2,
            childCategory3,
            childCategory4)
            .collect(Collectors.toCollection(LinkedHashSet::new)));

        parentCategory2.setChildCategories(Stream.of(
            childCategory5,
            childCategory6)
            .collect(Collectors.toCollection(LinkedHashSet::new)));

        return Stream.of(parentCategory1, parentCategory2)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}