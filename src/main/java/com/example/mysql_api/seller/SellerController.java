package com.example.mysql_api.seller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Sellers sellers){
        sellerService.saveSeller(sellers);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/get_rating/{seller_id}")
    public ResponseEntity<Integer> add(@PathVariable int seller_id){
        return ResponseEntity.ok(sellerService.getSellerRatingById(seller_id));
    }
}
