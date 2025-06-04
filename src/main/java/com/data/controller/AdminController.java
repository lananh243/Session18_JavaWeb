package com.data.controller;

import com.data.entity.Admin;
import com.data.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/save-login")
    public String saveLogin( @RequestParam String username,
                             @RequestParam String password,
                             HttpSession session, Model model){
        Admin admin = adminService.findByUsernameAndPassword(username, password);

        if(admin != null && "admin".equals(admin.getRole())){
            session.setAttribute("admin", admin);
            return "admin_dashboard";
        } else {
            model.addAttribute("error", "Mật khẩu hoặc tài khoản sai");
            return "login";
        }
    }


}
