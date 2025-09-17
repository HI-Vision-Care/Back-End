package com.hivision.hivision.service.cservice.Inventory;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.CheckoutRequest;
import com.hivision.hivision.payload.request.OrderRequest;
import com.hivision.hivision.pojo.Inventory.Order;
import com.hivision.hivision.pojo.Inventory.Product;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.repository.Inventory.IOrderRepo;
import com.hivision.hivision.repository.Inventory.IProductRepo;
import com.hivision.hivision.service.iservice.iInventory.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class OrderService implements IOrderService {

    IProductRepo productRepo;
    IOrderRepo orderRepo;
    IPatientRepo patientRepo;


    @Override
    @Transactional
    public Order addItemToOrder(OrderRequest request, String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));


        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        return orderRepo.save(Order.builder()
                .patient(patient)
                .product(product)
                .quantity(request.getQuantity())
                .amount(product.getPrice()*request.getQuantity())
                .build());
    }

    @Override
    public Order checkoutOrder(Integer orderId, CheckoutRequest checkoutRequest, String userId) {
        return null;
    }
}
