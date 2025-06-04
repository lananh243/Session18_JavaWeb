package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.dto.CustomerDTO;
import com.data.entity.Customer;
import com.data.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/customer")
    public String customerForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer_form";
    }

    @PostMapping("/customer-save")
    public String saveCustomer(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
                               BindingResult result, Model model) {

        try {
            if (result.hasErrors()) {
                return "customer_form";
            }

            Map<String, Object> uploadResult = cloudinary.uploader().upload(customerDTO.getFileImage().getBytes(), ObjectUtils.emptyMap());
            String fileImage = uploadResult.get("secure_url").toString();

            Customer customer = new Customer();
            customer.setId(customerDTO.getId());
            customer.setFirst_name(customerDTO.getFirst_name());
            customer.setLast_name(customerDTO.getLast_name());
            customer.setPhone(customerDTO.getPhone());
            customer.setAddress(customerDTO.getAddress());
            customer.setFileImage(fileImage);

            customerService.addCustomer(customer);

            return "redirect:/customer";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/customers";
        }
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer_list";
    }

    @GetMapping("/seach-name")
    public String seachCustomerName(@RequestParam("last_name") String last_name, Model model) {
        List<Customer> customers = customerService.searchCustomerByName(last_name);
        model.addAttribute("customers", customers);
        return "customer_list";
    }
}
