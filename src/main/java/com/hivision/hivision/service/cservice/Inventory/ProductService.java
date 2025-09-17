package com.hivision.hivision.service.cservice.Inventory;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.ProductRequest;
import com.hivision.hivision.pojo.Inventory.Category;
import com.hivision.hivision.pojo.Inventory.Product;
import com.hivision.hivision.pojo.Inventory.Supplier;
import com.hivision.hivision.repository.Inventory.ICategoryRepo;
import com.hivision.hivision.repository.Inventory.IProductRepo;
import com.hivision.hivision.repository.Inventory.ISupplierRepo;
import com.hivision.hivision.service.iservice.iInventory.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
class ProductService implements IProductService {

    IProductRepo  productRepo;
    ICategoryRepo categoryRepo;
    ISupplierRepo supplierRepo;

    @Override
    public void createProduct(ProductRequest request) {
        Category category = categoryRepo.findById(request.getCategoryID())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Supplier supplier = supplierRepo.findById(request.getSupplierID())
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));

        Product newProduct = Product.builder()
                .sku(request.getSku())
                .productName(request.getProductName())
                .price(request.getPrice())
                .description(request.getDes())
                .unit(request.getUnit())
                .imageUrl(request.getImg())
                .category(category)
                .supplier(supplier)
                .build();
        productRepo.save(newProduct);
    }

    @Override
    public Product updateProduct(Product product) {
        Product updateProduce = Product.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .description(product.getDescription())
                .unit(product.getUnit())
                .imageUrl(product.getImageUrl())
                .build();
        return productRepo.save(updateProduce);
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }
}
