package com.example.mysql_api.item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Integer> {
    @Query(value = "SELECT items.* FROM items WHERE items.seller_id=?1",nativeQuery=true)
    List<Items> findBysellerId(int seller_id);

    @Query(value = "SELECT items.* FROM items WHERE items.item_category=?1 OR (keyword1 IN (?2,?3,?4,?5,?6) OR keyword2 IN (?2,?3,?4,?5,?6) or keyword3 IN (?2,?3,?4,?5,?6) or keyword4 IN (?2,?3,?4,?5,?6) or keyword5 IN (?2,?3,?4,?5,?6))",nativeQuery=true)
    List<Items> searchByCategoryAndKeywords(int category,String k1,String k2,String k3,String k4,String k5);
}
