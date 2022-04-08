package com.example.mysql_api.buyer;

import com.example.mysql_api.item.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyers,Integer> {
    @Query(value = "SELECT buyers.* FROM buyers WHERE buyers.buyer_name=?1",nativeQuery=true)
    Buyers findByBuyer_name(String buyer_name);
}
