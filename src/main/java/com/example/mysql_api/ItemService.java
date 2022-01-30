package com.example.mysql_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<Items> listAllItem() {
        return itemRepository.findAll();
    }

    public void saveItem(Items item) {
        itemRepository.save(item);
    }

    public Items getItem(Integer id) {
        return itemRepository.findById(id).get();
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    public List<Items> findBySellerId(Integer id){return itemRepository.findBysellerId(id);}
}
