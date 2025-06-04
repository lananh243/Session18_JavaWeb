package com.data.service;

import com.data.entity.Admin;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);
}
