package com.example.mysql_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    public List<Sellers> listAllSeller() {
        return sellerRepository.findAll();
    }

    public void saveSeller(Sellers seller) {
        sellerRepository.save(seller);
    }

    public Sellers getSeller(Integer id) {
        return sellerRepository.findById(id).get();
    }

    public void deleteSeller(Integer id) {
        sellerRepository.deleteById(id);
    }
}
