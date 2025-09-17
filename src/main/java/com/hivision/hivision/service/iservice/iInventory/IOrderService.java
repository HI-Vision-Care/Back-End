package com.hivision.hivision.service.iservice.iInventory;

import com.hivision.hivision.payload.request.CheckoutRequest;
import com.hivision.hivision.payload.request.OrderRequest;
import com.hivision.hivision.pojo.Inventory.Order;

public interface IOrderService {
    Order addItemToOrder(OrderRequest request, String patientID);
    Order checkoutOrder(Integer orderId, CheckoutRequest checkoutRequest, String userId);

}
