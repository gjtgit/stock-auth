package com.gao.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gao.demo.entity.UserEntity;
import com.gao.demo.repository.UserRepo;

@Service
public class UserService {
    
    Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepo userRepo;
    
    public void save(UserEntity u) throws Exception{
        try{
            userRepo.saveAndFlush(u);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public List<UserEntity> getUserList() throws Exception{
        return userRepo.findAll();
    }
    
    public UserEntity findById(Long id) throws Exception{
        UserEntity user = null;
        try {
            Optional<UserEntity> u = userRepo.findById(id);
            if(u.isPresent()) {
                user = u.get();
            }
            return user;
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public UserEntity findByUsernameAndPassword(String username, String password) throws Exception{
        return userRepo.findByUsernameAndPassword(username, password);
    }        

}
