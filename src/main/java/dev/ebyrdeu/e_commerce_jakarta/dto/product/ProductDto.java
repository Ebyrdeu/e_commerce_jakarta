package dev.ebyrdeu.e_commerce_jakarta.dto.product;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Product;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDto(CategoryDto category, Set<OrderDto> orders, String product_name, String product_description, BigDecimal price,
                         Integer stock) {
    public static ProductDto map(Product entity) {
        return new ProductDto(CategoryDto.map(entity.category()),
                entity.orders().stream().map(OrderDto::map).collect(Collectors.toSet())
                , entity.name(), entity.description(), entity.price(), entity.stock());
    }

    public static Product map(ProductDto productDto) {
        var product = new Product();
        product.setName(productDto.product_name());
        product.setStock(productDto.stock());
        product.setDescription(productDto.product_description());
        product.setPrice(productDto.price());
        product.setCategory(CategoryDto.map(productDto.category()));
        product.setOrders(productDto.orders().stream().map(OrderDto::map).collect(Collectors.toSet()));
        return product;
    }
}

