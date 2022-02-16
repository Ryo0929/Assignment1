package com.example.mysql_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/buyers")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/add")
    public void add(@RequestBody Buyers buyers){buyerService.saveBuyer(buyers);}
}
