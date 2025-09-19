package com.hivision.hivision.service.iservice.iInventory;

import com.hivision.hivision.dto.ProductDTO;
import com.hivision.hivision.payload.request.ProductRequest;
import com.hivision.hivision.pojo.Inventory.Product;

import java.util.List;

public interface IProductService {
    void createProduct(ProductRequest product);
    void updateProduct(ProductDTO productDTO, Integer productId);
    void unactiveProduct(Integer productID);
    void activeProduct(Integer productID);
    List<Product> findAllProducts();

}
