package com.data.repository;

import com.data.entity.Admin;

public interface AdminRepository {
    Admin findByUsernameAndPassword(String username, String password);
}
