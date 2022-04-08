package com.example.mysql_api.item;
import com.example.model.Response;
import com.example.mysql_api.GrpcService;
import com.example.mysqljdbc.item;
import com.example.mysqljdbc.itemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequestMapping(path="/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private GrpcService grpcService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Items item) {
        if(item.getSeller_id()==null){
            return new ResponseEntity<>("seller_id_not_assigned", HttpStatus.BAD_REQUEST);
        }
        int id = UUID.randomUUID().hashCode();
        System.out.println(id);
        item.setItem_id(id);
        grpcService.saveItem(item);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(path="/list_item_by_seller_id/{seller_id}")
    public ResponseEntity<List<Items>> getBySellerId(@PathVariable Integer seller_id){
        Items query_item=new Items();
        query_item.setSeller_id(seller_id);
        itemList result=grpcService.getItem(query_item);
        List<Items> items = convertGrpcItem2obj(result);
        printItems(items);
        return ResponseEntity.ok(items);
    }

    @GetMapping(path="/list_item_by_keyword_and_category")
    public ResponseEntity<List<Items>> getSearchItemByCategoryAndKeyword(@RequestBody Items item){
        List<Items>res=itemService.searchItems(item);
        printItems(res);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update_price/{id}")
    public ResponseEntity<?> update(@RequestBody Items inputItem, @PathVariable Integer id) {
        Double new_price = inputItem.getSale_price();
        Items i = new Items();
        i.setItem_id(id);
        itemList existItem = grpcService.getItem(i);
        if (existItem.getItemsCount()!=0){
            item grpcRes = existItem.getItems(0);
            Items res= convertItem(grpcRes);
            res.setSale_price(new_price);
            grpcService.saveItem(res);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/remove_item/{id}/{remove_quantity}")
    public ResponseEntity update(@PathVariable Integer id,@PathVariable Integer remove_quantity) {
        item target= getItemByItemId(id);
        Items query_item = new Items();
        Integer current_quantity=target.getQuantity();

        if(remove_quantity>=current_quantity){
            query_item.setItem_id(id);
            query_item.setSeller_id(target.getSellerId());
            grpcService.delItem(query_item);
        }else{
            query_item=convertItem(target);
            query_item.setQuantity(current_quantity-remove_quantity);
            grpcService.saveItem(query_item);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    private item getItemByItemId(Integer id){
        Items query_item=new Items();
        query_item.setItem_id(id);
        itemList result=grpcService.getItem(query_item);
        item i = result.getItems(0);
        return i;
    }
    private void printItems(List<Items> items){
        for(Items item:items){
            System.out.println("id: "+item.getItem_id());
            System.out.println("name: "+item.getItem_name());
            System.out.println("cate: "+item.getItem_category());
            System.out.println("price: "+item.getSale_price());
            System.out.println("quantity: "+item.getQuantity());
            System.out.println("k1: "+item.getKeyword1());
            System.out.println("k2: "+item.getKeyword2());
            System.out.println("k3: "+item.getKeyword3());
            System.out.println("---------------");
        }
    }
    private List<Items> convertGrpcItem2obj(itemList items){
        List<Items> res=new ArrayList<>();
        for(item i : items.getItemsList()){
            res.add(convertItem(i));
        }
        return res;
    }
    private Items convertItem(item i){
        Items item = new Items();
        item.setItem_id(i.getItemId());
        item.setItem_category(i.getItemCategory());
        item.setItem_name(i.getItemName());
        item.setItem_condition(i.getItemCondition());
        item.setSale_price(i.getSalePrice());
        item.setQuantity(i.getQuantity());
        item.setKeyword1(i.getKeyword1());
        item.setKeyword2(i.getKeyword2());
        item.setKeyword3(i.getKeyword3());
        item.setKeyword4(i.getKeyword4());
        item.setKeyword5(i.getKeyword5());
        item.setSeller_id(i.getSellerId());
        return item;
    }
}
