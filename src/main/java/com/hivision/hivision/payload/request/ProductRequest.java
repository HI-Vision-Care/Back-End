package com.hivision.hivision.payload.request;

import com.hivision.hivision.pojo.Inventory.Category;
import com.hivision.hivision.pojo.Inventory.Supplier;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String sku;
    String productName;
    String des;
    Double price;
    String unit;
    String img;
    Integer categoryID;
    Integer supplierID;

}
