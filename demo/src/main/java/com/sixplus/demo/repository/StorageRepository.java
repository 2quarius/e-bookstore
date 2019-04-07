package com.sixplus.demo.repository;

import com.sixplus.demo.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage,Integer> {
}
