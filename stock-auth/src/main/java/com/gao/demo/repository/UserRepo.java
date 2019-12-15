package com.gao.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gao.demo.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long>{
    
    UserEntity findByCheckCode(String checkCode);
    public UserEntity findByUsername(String username);
    public UserEntity findByUsernameAndPassword(String username, String password);
}
