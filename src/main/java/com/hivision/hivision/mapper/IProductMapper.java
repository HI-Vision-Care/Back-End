package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.ProductDTO;
import com.hivision.hivision.pojo.Inventory.Product;
import org.mapstruct.Mapper;

@Mapper
public interface IProductMapper {
    Product toProduct(ProductDTO productDTO);
    ProductDTO toProductDTO(Product product);
}
