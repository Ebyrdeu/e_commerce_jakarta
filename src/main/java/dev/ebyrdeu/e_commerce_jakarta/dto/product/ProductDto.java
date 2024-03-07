package dev.ebyrdeu.e_commerce_jakarta.dto.product;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDto(
        Long id,
        CategoryDto category,
        Set<OrderDto> orders,

        @NotBlank(message = "Name of Product  can't be null")
        @Size(min = 2, max = 15, message = "Name of Product must be between 2 and 15 chars")
        String product_name,

        @Size(min = 2, message = "Description of Category must be at least 2 char long")
        String product_description,

        @PositiveOrZero(message = "Price of Product must not be negative")
        BigDecimal price,

        @PositiveOrZero(message = "Price of Product must not be negative")
        Integer stock,

        Date created_at,
        Date updated_at
) {
    public static ProductDto map(Product entity) {
        return new ProductDto(
                entity.id(),
                CategoryDto.map(entity.category()),
                entity.orders().stream().map(OrderDto::map).collect(Collectors.toSet()),
                entity.name(),
                entity.description(),
                entity.price(),
                entity.stock(),
                entity.createdAt(),
                entity.updatedAt()
        );
    }

    public static Product map(ProductDto productDto) {
        var product = new Product();
        product.setId(productDto.id);
        product.setName(productDto.product_name);
        product.setStock(productDto.stock);
        product.setDescription(productDto.product_description);
        product.setPrice(productDto.price);
        product.setCategory(CategoryDto.map(productDto.category));
        product.setOrders(productDto.orders().stream().map(OrderDto::map).collect(Collectors.toSet()));
        product.setCreatedAt(productDto.created_at);
        product.setUpdatedAt(productDto.updated_at);
        return product;
    }
}

