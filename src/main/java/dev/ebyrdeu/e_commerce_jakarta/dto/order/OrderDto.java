package dev.ebyrdeu.e_commerce_jakarta.dto.order;

import dev.ebyrdeu.e_commerce_jakarta.dto.customer.CustomerDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.product.ProductDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import dev.ebyrdeu.e_commerce_jakarta.entity.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderDto(CustomerDto customer, Set<ProductDto> products, Status status, Instant orderDate,
                       BigDecimal total) {
    public static OrderDto map(Order entity) {
        return new OrderDto(
                CustomerDto.map(entity.customer()),
                entity.products().stream().map(ProductDto::map).collect(Collectors.toSet()),
                entity.status(),
                entity.orderDate(),
                entity.total()
        );
    }

    public static Order map(OrderDto orderDto) {
        var order = new Order();
        order.setStatus(orderDto.status());
        order.setOrderDate(orderDto.orderDate());
        order.setTotal(orderDto.total());
        order.setCustomer(CustomerDto.map(orderDto.customer()));
        order.setProducts(orderDto.products().stream().map(ProductDto::map).collect(Collectors.toSet()));

        return order;
    }
}
