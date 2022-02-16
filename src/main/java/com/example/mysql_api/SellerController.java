package com.example.mysql_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/add")
    public void add(@RequestBody Sellers sellers){
        sellerService.saveSeller(sellers);
    }
    @GetMapping("/get_rating/{seller_id}")
    public ResponseEntity<Integer> add(@PathVariable int seller_id){
        return ResponseEntity.ok(sellerService.getSellerRatingById(seller_id));
    }
}
