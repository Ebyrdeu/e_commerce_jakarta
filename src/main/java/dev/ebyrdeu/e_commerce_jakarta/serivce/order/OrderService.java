package dev.ebyrdeu.e_commerce_jakarta.serivce.order;

import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.order.Orders;
import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import dev.ebyrdeu.e_commerce_jakarta.serivce.Service;

public interface OrderService extends Service<Order, OrderDto, Orders> {
}
