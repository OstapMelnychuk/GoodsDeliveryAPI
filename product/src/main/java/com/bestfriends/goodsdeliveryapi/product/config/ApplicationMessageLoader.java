package com.bestfriends.goodsdeliveryapi.product.config;

import com.bestfriends.goodsdeliveryapi.product.cache.ApplicationMessageCash;
import com.bestfriends.goodsdeliveryapi.product.cache.entities.CachedApplicationMessage;
import com.bestfriends.goodsdeliveryapi.product.repositories.ApplicationMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ApplicationMessageLoader {
    private final ApplicationMessageCash cash;
    private final ApplicationMessageRepository applicationMessageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ApplicationMessageLoader(ApplicationMessageCash cash,
                                    ApplicationMessageRepository applicationMessageRepository,
                                    ModelMapper modelMapper) {
        this.cash = cash;
        this.applicationMessageRepository = applicationMessageRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void loadMessagesIntoCache() {
        List<CachedApplicationMessage> applicationMessages =
                applicationMessageRepository.findAll().stream()
                        .map(m -> modelMapper.map(m, CachedApplicationMessage.class))
                        .collect(Collectors.toList());

        cash.deleteAll(applicationMessages);
        cash.saveAll(applicationMessages);
    }
}
