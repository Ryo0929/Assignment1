package com.example.mysql_api.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;
    public void saveBuyer(Buyers buyers){
        buyerRepository.save(buyers);
    }
}
