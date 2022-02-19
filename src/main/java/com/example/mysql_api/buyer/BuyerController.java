package com.example.mysql_api.buyer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/buyers")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/add")
    public ResponseEntity add(
        @RequestBody Buyers buyers){buyerService.saveBuyer(buyers);
        return ResponseEntity.ok().build();
    }
}
