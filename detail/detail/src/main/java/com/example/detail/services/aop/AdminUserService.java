package com.example.detail.services.aop;

import com.example.detail.models.User;

import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    public final User adminUser = new User("202101166");
    
    public void login() {
        System.out.println("admin user login...");
    }

    public User getAdminUser() {
        return adminUser;
    }
}