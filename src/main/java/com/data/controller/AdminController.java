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
import java.util.List;

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

    @GetMapping("/list-admin")
    public String listAdmin(Model model){
        List<Admin> admins = adminService.findByRole("user");
        model.addAttribute("admins", admins);
        return "list_user";
    }

    @PostMapping("/toggle-lock")
    public String toggleLock(@RequestParam("id") int id,
                             @RequestParam("status") boolean status){
        adminService.changeUserStatus(id, status);
        return "redirect:/list-admin";
    }

    @GetMapping("/search-username")
    public String searchByUsername(@RequestParam("username") String username, Model model) {
        List<Admin> admins = adminService.searchByName(username);
        model.addAttribute("admins", admins);
        return "list_user";
    }

}
