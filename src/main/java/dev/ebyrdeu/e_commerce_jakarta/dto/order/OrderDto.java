package dev.ebyrdeu.e_commerce_jakarta.dto.order;

import dev.ebyrdeu.e_commerce_jakarta.dto.customer.CustomerDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.product.ProductDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import dev.ebyrdeu.e_commerce_jakarta.entity.Status;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderDto(
        Long id,
        CustomerDto customer,
        Set<ProductDto> products,
        Status status,
        Date orderDate,

        @PositiveOrZero(message = "Orders sizes must not be negative")
        BigDecimal total,

        Date created_at,
        Date updated_at
) {
    public static OrderDto map(Order entity) {
        return new OrderDto(
                entity.id(),
                CustomerDto.map(entity.customer()),
                entity.products().stream().map(ProductDto::map).collect(Collectors.toSet()),
                entity.status(),
                entity.orderDate(),
                entity.total(),
                entity.createdAt(),
                entity.updatedAt()
        );
    }

    public static Order map(OrderDto orderDto) {
        var order = new Order();
        order.setId(orderDto.id);
        order.setStatus(orderDto.status);
        order.setOrderDate(orderDto.orderDate);
        order.setTotal(orderDto.total);
        order.setCustomer(CustomerDto.map(orderDto.customer));
        order.setProducts(orderDto.products().stream().map(ProductDto::map).collect(Collectors.toSet()));
        order.setCreatedAt(orderDto.created_at);
        order.setUpdatedAt(orderDto.updated_at);
        return order;
    }
}
