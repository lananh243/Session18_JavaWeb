package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    @NotBlank(message = "First name không được để trống")
    @Size(min = 3, max = 10, message = "First name phải từ 3 đến 10 ký tự")
    private String first_name;

    @NotBlank(message = "Last name không được để trống")
    @Size(min = 3, max = 10, message = "Last name phải từ 3 đến 10 ký tự")
    private String last_name;

    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String address;
    private MultipartFile fileImage;
}
