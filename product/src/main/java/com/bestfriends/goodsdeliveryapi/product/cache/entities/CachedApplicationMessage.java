package com.bestfriends.goodsdeliveryapi.product.cache.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

<<<<<<< Updated upstream
/**
 * Instances of this class can be saved to cache.
 * {@link RedisHash} annotation defined key which will be used by cache. It should be unique.
 */
=======
>>>>>>> Stashed changes
@RedisHash("cachedApplicationMessage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedApplicationMessage {
    private Long id;

    private String message;
}
