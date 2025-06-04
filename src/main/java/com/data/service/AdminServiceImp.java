package com.data.service;

import com.data.entity.Admin;
import com.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private AdminRepository adminRepo;
    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Admin> findByRole(String role) {
        return adminRepo.findByRole(role);
    }

    @Override
    public void changeUserStatus(int id, boolean status) {
        adminRepo.changeUserStatus(id, status);
    }

    @Override
    public List<Admin> searchByName(String username) {
        return adminRepo.searchByName(username);
    }


}
