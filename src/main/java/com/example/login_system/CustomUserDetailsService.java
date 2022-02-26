package com.example.login_system;

import com.example.mysql_api.buyer.BuyerRepository;
import com.example.mysql_api.buyer.Buyers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private BuyerRepository buyerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Buyers buyer = buyerRepo.findByBuyer_name(username);
        if (buyer == null) {
            throw new UsernameNotFoundException("buyer not found");
        }
        return new CustomUserDetails(buyer);
    }

}
