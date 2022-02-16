package com.example.mysql_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyers,Integer> {
}
