package com.example.mysql_api.purchasedItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class purchasedItemService {
    @Autowired
    private purchasedItemRepository purchasedItemRepository;

    public void add(purchasedItem transaction){
        purchasedItemRepository.save(transaction);
    }
    public List<purchasedItem> listByBuyerId(Integer buyer_id) {
        return purchasedItemRepository.findByBuyerId(buyer_id);
    }
}