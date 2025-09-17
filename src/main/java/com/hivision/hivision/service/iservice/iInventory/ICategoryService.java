package com.hivision.hivision.service.iservice.iInventory;

import com.hivision.hivision.pojo.Inventory.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}
