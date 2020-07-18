package com.bestfriends.goodsdeliveryapi.product.cache;

import com.bestfriends.goodsdeliveryapi.product.cache.entities.CachedApplicationMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationMessageCash extends CrudRepository<CachedApplicationMessage, Long> {
}
