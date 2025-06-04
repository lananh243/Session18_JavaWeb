package com.data.controller;

import com.data.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null || !"admin".equals(admin.getUsername())) {
            return "redirect:/login";
        }
        return "admin_dashboard";
    }

}
