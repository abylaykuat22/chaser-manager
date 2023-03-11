package com.chasermanager.repositories;

import com.chasermanager.domain.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByNameAndPriceAndLink(String name, String price, String link);

}
