package com.bestfriends.goodsdeliveryapi.product.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

<<<<<<< Updated upstream
/**
 * {@link ModelMapper} configuration.
 * {@link ModelMapper} used for mapping one class to another.
 */
=======
>>>>>>> Stashed changes
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
