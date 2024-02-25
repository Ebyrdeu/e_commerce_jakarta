package dev.ebyrdeu.e_commerce_jakarta.serivce.product;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.product.ProductDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.product.Products;
import dev.ebyrdeu.e_commerce_jakarta.entity.Product;
import dev.ebyrdeu.e_commerce_jakarta.repository.category.CategoryRepositroy;
import dev.ebyrdeu.e_commerce_jakarta.repository.poduct.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CategoryRepositroy categoryRepositroy;

    public ProductServiceImpl() {

    }

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepositroy categoryRepositroy) {
        this.productRepository = productRepository;
        this.categoryRepositroy = categoryRepositroy;
    }

    @Override
    public Products getAll() {
        return new Products(
                productRepository.getAll().stream().map(ProductDto::map).toList()
        );
    }

    @Override
    public Product getOne(long id) {
        var product = productRepository.getOne(new Product().setId(id));

        if (product == null) {
            throw new NotFoundException("Product with id not found");
        }

        return product;
    }

    @Override
    public Product create(ProductDto dto) {
        var category = Optional.ofNullable(dto.category())
                .map(categoryDto -> categoryRepositroy.getOne(CategoryDto.map(categoryDto)))
                .orElseThrow(() -> new NotFoundException("Category" + dto.category() + " not found"));

        var product = ProductDto.map(dto).setCategory(category);
//        TODO: change to DTO
        return productRepository.create(product);
    }

    @Override
    public Product update(long id, ProductDto productDto) {

        return productRepository.update(
                new Product()
                        .setId(id)
                        .setCategory(CategoryDto.map(productDto.category()))
                        .setDescription(productDto.product_description())
                        .setPrice(productDto.price())
                        .setName(productDto.product_name())
                        .setStock(productDto.stock())
                        .setOrders(productDto.orders().stream().map(OrderDto::map).collect(Collectors.toSet()))
        );
    }

    @Override
    public void remove(long id) {
        productRepository.remove(new Product().setId(id));
    }


}
