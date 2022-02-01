package com.example.mysql_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query(value="DELETE FROM shoppingcart where item_id=?1",nativeQuery=true)
    void deleteByItemId(int itemId);

    @Modifying
    @Query(value="DELETE FROM shoppingcart where buyer_id=?1",nativeQuery=true)
    int clearByBuyerId(int BuyerId);

    @Query(value="SELECT * FROM shoppingcart where buyer_id=?1",nativeQuery=true)
    List<ShoppingCart> getByBuyerId(int BuyerId);
    @Query(value="SELECT * FROM shoppingcart where item_id=?1 AND buyer_id=?2",nativeQuery=true)
    List<ShoppingCart> getByBuyerIdAndItemId(int ItemId, int BuyerId);
}
