package com.sixplus.demo.repository;

import com.sixplus.demo.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal,String> {
    List<Deal> findByBuyername(String buyername);
    List<Deal> findAll();
}
