package com.example.atomic;

import com.example.mysql_api.buyer.BuyerService;
import com.example.mysql_api.buyer.Buyers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Brayden
 * @create 4/5/22 5:19 PM
 * @Description
 */
@Controller
@RequestMapping(path="/atomic")
public class AtomicController {

//    @PostMapping("/add")
//    public ResponseEntity add(
//            @RequestBody Buyers buyers){buyerService.saveBuyer(buyers);
//        return ResponseEntity.ok().build();
//    }
}
