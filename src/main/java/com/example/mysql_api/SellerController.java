package com.example.mysql_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/add")
    public void add(@RequestBody Sellers sellers){
        sellerService.saveSeller(sellers);
    }
}
