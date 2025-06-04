package com.data.service;

import com.data.entity.Admin;
import com.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private AdminRepository adminRepo;
    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminRepo.findByUsernameAndPassword(username, password);
    }
}
