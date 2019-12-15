package com.gao.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gao.demo.entity.UserEntity;
import com.gao.demo.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserEntity loginUser = userRepo.findByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException(username);
        }
        //If userType = admin or userName = admin, give ROLE_ADMIN; others give RROLE_USER
        if("admin".equalsIgnoreCase(username) ||
            "admin".equalsIgnoreCase(loginUser.getUserType())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(loginUser.getUsername(),loginUser.getPassword(),authorities);
    }

}
