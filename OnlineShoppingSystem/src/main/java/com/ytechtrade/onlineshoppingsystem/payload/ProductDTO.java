package com.ytechtrade.onlineshoppingsystem.payload;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 255, message = "Product name must contain between 3 and 255 characters")
    private String productName;

    private String image;

    @NotBlank(message = "Product description is required")
    @Size(min = 6, max = 255, message = "Product description must contain between 6 and 255 characters")
    private String description;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private double price;

    @DecimalMin(value = "0.0", message = "Discount must be greater than or equal to 0")
    @DecimalMax(value = "100.0", message = "Discount must be less than or equal to 100")
    private double discount;

    @PositiveOrZero(message = "Special price must be greater than or equal to 0")
    private double specialPrice;
}
