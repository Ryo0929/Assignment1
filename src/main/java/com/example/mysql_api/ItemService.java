package com.example.mysql_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<Items> listAllItem() {
        System.out.println("list");
        return itemRepository.findAll();
    }
    //Put an item for sale: provide all item characteristics and quantity
    public void saveItem(Items item) {
        itemRepository.save(item);
    }
    //Change the sale price of an item: provide item id and new sale price
    public void changeSalePrice(Items item){
        Items existItem=getItem(item.getItem_id());
        existItem.setSale_price(item.getSale_price());
        saveItem(existItem);
    }
    //Remove an item from sale: provide item id and quantity
    public void removeItem(Items item,int remove_quantity){
        int item_id=item.getItem_id();
        Items existItem=getItem(item_id);

        //Didn't deal with the case that remove quantity is larger than current quantity. I just deleted it if so.
        if (existItem.getQuantity()<=remove_quantity){
            deleteItem(item_id);
        }else{
            existItem.setQuantity(existItem.getQuantity()-remove_quantity);
            saveItem(existItem);
        }
    }

    public Items getItem(Integer id) {
        return itemRepository.findById(id).get();
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    //Display items currently on sale put up by this seller
    public List<Items> findBySellerId(Integer seller_id) {return itemRepository.findBysellerId(seller_id);}
    //Search items for sale: provide an item category and up to five keywords
    public List<Items> searchItems(Items item){return itemRepository.searchByCategoryAndKeywords(item.getItem_category(),item.getKeyword1(),item.getKeyword2(),item.getKeyword3(),item.getKeyword4(),item.getKeyword5());}
}
