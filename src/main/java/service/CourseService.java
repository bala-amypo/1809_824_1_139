package com.example.demo.service.impls;

import com.example.demo.service.AuditTrailRecordService;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.entity.AuditTrailRecord;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AuditTrailRecordImpls implements AuditTrailRecordService{
    
    @Autowired
    AuditTrailRecordRepository atr;

    public AuditTrailRecord logEvent(AuditTrailRecord record){
        return atr.save(record);
    }

    public List<AuditTrailRecord> getLogsByCredential(Long credentialId){
        return atr.findByCredentialId(credentialId);
    }

    public List<AuditTrailRecord> getAllLogs(){
        return atr.findAll();
    }
}