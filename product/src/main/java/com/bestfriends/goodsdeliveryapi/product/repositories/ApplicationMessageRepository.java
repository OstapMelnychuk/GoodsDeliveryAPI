package com.bestfriends.goodsdeliveryapi.product.repositories;

import com.bestfriends.goodsdeliveryapi.product.model.ApplicationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
/**
 * Repository used for working with {@link ApplicationMessage}.
 */
=======
>>>>>>> Stashed changes
@Repository
public interface ApplicationMessageRepository extends JpaRepository<ApplicationMessage, Long> {
}
