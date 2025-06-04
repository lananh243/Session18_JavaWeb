package com.data.repository;

import com.data.entity.Admin;

import java.util.List;

public interface AdminRepository {
    Admin findByUsernameAndPassword(String username, String password);
    List<Admin> findByRole(String role);
    void changeUserStatus(int id, boolean status);
    List<Admin> searchByName(String username);
}
