package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TransferRule;
public interface TransferRuleRepository extends JpaRepository<TransferRule,Long>{
    
}