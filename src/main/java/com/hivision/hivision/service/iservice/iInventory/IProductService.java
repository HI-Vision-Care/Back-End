package com.hivision.hivision.service.iservice.iInventory;

import com.hivision.hivision.payload.request.ProductRequest;
import com.hivision.hivision.pojo.Inventory.Product;

import java.util.List;

public interface IProductService {
    void createProduct(ProductRequest product);
    Product updateProduct(Product product);
    void deleteProduct(Product product);
    List<Product> findAllProducts();
}
