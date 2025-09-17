package com.hivision.hivision.service.cservice.Inventory;

import com.hivision.hivision.pojo.Inventory.Category;
import com.hivision.hivision.repository.Inventory.ICategoryRepo;
import com.hivision.hivision.service.iservice.iInventory.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CategoryService implements ICategoryService {
    ICategoryRepo categoryRepo;


    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
