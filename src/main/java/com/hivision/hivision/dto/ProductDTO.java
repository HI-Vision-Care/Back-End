package com.hivision.hivision.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.hivision.hivision.pojo.Inventory.Product}
 */
@Value
public class ProductDTO implements Serializable {
    @NotNull
    @Size(max = 50)
    String sku;
    @NotNull
    @Size(max = 255)
    String productName;
    String description;
    @NotNull
    Double price;
    @Size(max = 50)
    String unit;
    String imageUrl;
}