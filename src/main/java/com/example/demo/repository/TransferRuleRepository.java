package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.TransferRule;
import java.util.Long;
@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    }