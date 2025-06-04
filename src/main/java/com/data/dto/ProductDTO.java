package com.data.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "Giá sản phẩm không được < 0")
    private double price;

    @Min(value = 0, message = "Tồn kho không được < 0")
    private int stock;
    private MultipartFile image;
}
