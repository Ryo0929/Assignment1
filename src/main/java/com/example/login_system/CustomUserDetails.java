package com.example.login_system;

import com.example.mysql_api.buyer.Buyers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
public class CustomUserDetails implements UserDetails {
    private Buyers buyers;

    public CustomUserDetails(Buyers buyers){
        this.buyers=buyers;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(buyers.getBuyer_password());
    }

    @Override
    public String getUsername() {
        return buyers.getBuyer_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
