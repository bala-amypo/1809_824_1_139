package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.entity.*;

public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    List<TransferRule> findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(Long s, Long t);
}


