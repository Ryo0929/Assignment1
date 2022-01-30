package com.example.mysql_api;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Integer> {
    @Query(value = "SELECT items.* FROM items WHERE items.seller_id=?1",nativeQuery=true)
    List<Items> findBysellerId(int seller_id);
}
