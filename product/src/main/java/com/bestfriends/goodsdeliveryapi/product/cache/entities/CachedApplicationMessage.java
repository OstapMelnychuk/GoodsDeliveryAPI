package com.bestfriends.goodsdeliveryapi.product.cache.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("cachedApplicationMessage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedApplicationMessage {
    private Long id;

    private String message;
}
