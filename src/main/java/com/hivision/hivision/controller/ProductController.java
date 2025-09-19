package com.hivision.hivision.controller;

import com.hivision.hivision.dto.ProductDTO;
import com.hivision.hivision.payload.request.ProductRequest;
import com.hivision.hivision.pojo.Inventory.Product;
import com.hivision.hivision.service.iservice.iInventory.IProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/product")
class ProductController {
    IProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest request){
        productService.createProduct(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PutMapping("/update/{productID}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO dto, @PathVariable Integer productID){
        productService.updateProduct(dto, productID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/active/{productID}")
    public ResponseEntity<Void> activeProduct(@PathVariable Integer productID){
        productService.activeProduct(productID);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/unactive/{productID}")
    public ResponseEntity<Void> unactiveProduct(@PathVariable Integer productID){
        productService.unactiveProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
