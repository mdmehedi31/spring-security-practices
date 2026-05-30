package com.sps.service;

import com.sps.entity.LogginRepository;
import com.sps.entity.LoggingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoggingService {

    @Autowired
    private LogginRepository logginRepository;


    public LoggingEntity saveLog(LoggingEntity loggingEntity) {
    try {
        return logginRepository.save(loggingEntity);
    }catch (Exception e) {
        throw e;
    }
    }

    public List<LoggingEntity> getAllLogged() {
        try{
            return logginRepository.findAll();
        }catch (Exception e) {
            throw e;
        }
    }

    @PostAuthorize("returnObject.createdByName == authentication.details.userName")
    public LoggingEntity getLoggedById(int id) {
        try{
            return this.logginRepository.findById(id).
                    orElseThrow(()->new NoSuchElementException("Loggin Not Found"));
        }catch (Exception e) {
            throw e;
        }
    }

}
