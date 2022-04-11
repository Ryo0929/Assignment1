package com.example.mysql_api.seller;


import com.example.atomic.SentPacket;
import com.example.atomic.UdpUnicastServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path="/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    UdpUnicastServer udpUnicastServer;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Sellers sellers){

//        udpUnicastServer.broadcast();

        sellerService.saveSeller(sellers);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/get_rating/{seller_id}")
    public ResponseEntity add(@PathVariable int seller_id){
        SentPacket packet = new SentPacket(0, " ", -1, -1, seller_id, 1);
        udpUnicastServer.broadcast(packet);
//        return ResponseEntity.ok(sellerService.getSellerRatingById(seller_id));
        return ResponseEntity.ok("success");
    }
}
