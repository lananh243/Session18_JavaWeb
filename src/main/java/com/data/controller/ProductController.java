package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.dto.ProductDTO;
import com.data.entity.Product;
import com.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("productDTO",  new ProductDTO());
        return "product_form";
    }

    @PostMapping("/save-product")
    public String saveProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
                              BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return "product_form";
            }

            if (productDTO.getImage() == null ||  productDTO.getImage().isEmpty()) {
                result.rejectValue("image", "error.productDTO", "Vui lòng chọn ảnh sản phẩm.");
                return "product_form";
            }

            Map uploadResult = cloudinary.uploader().upload(productDTO.getImage().getBytes(), ObjectUtils.emptyMap());
            String image = uploadResult.get("secure_url").toString();

            // Chuyển đổi DTO sang Entity
            Product product = new Product();
            product.setProductName(productDTO.getProductName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setImage(image);

            // Lưu vào DB
            productService.addProduct(product);

            redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công!");
            return "redirect:/products";

        }catch (IOException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi khi tải ảnh lên Cloudinary!");
            return "redirect:/admin/products";
        }
    }

    @GetMapping("/products")
    public String productsList(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size) {
        List<Product> products = productService.findAllProduct(page, size);
        long totalProducts = productService.countProducts(); // Tổng số sản phẩm
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "product_list";
    }

    @GetMapping("/filter-product")
    public String filterProduct(Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "minPrice", defaultValue = "0") double minPrice,
                                @RequestParam(name = "maxPrice", defaultValue = "1000000000") double maxPrice){
        List<Product> products = productService.filterProductByPrice(minPrice, maxPrice, page, size);
        long totalProducts = productService.countFilteredProducts(minPrice, maxPrice);
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "product_list";
    }
}
