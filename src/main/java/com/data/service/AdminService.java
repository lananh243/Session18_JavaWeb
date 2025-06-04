package com.data.service;

import com.data.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);
    List<Admin> findByRole(String role);
    void changeUserStatus(int id, boolean status);
    List<Admin> searchByName(String username);
}
