package com.bestfriends.goodsdeliveryapi.product;

import com.bestfriends.goodsdeliveryapi.product.cache.ApplicationMessageCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 */
@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Autowired
    ApplicationMessageCash cash;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(cash.findById(1L));
    }
}
