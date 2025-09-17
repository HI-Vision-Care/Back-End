package com.hivision.hivision.service.cservice.Inventory;

import com.hivision.hivision.pojo.Inventory.Supplier;
import com.hivision.hivision.repository.Inventory.ISupplierRepo;
import com.hivision.hivision.service.iservice.iInventory.ISupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SupplierService implements ISupplierService {
    ISupplierRepo supplierRepo;

    @Override
    public List<Supplier> findAllSupplier() {
        return supplierRepo.findAll();
    }
}
