package dev.ebyrdeu.e_commerce_jakarta.serivce.product;

import dev.ebyrdeu.e_commerce_jakarta.dto.product.ProductDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Product;
import dev.ebyrdeu.e_commerce_jakarta.repository.category.CategoryRepository;
import dev.ebyrdeu.e_commerce_jakarta.repository.poduct.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductServiceImpl() {

    }

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.getAll().stream().map(ProductDto::map).toList();
    }

    @Override
    public Product getOne(long id) {
        return productRepository.getOne(id).orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));
    }

    @Override
    @Transactional
    public Product create(ProductDto dto) {
        return productRepository.create(ProductDto.map(dto));
    }

    @Override
    @Transactional
    public void update(long id, ProductDto productDto) {
        var existingProduct = productRepository.getOne(id).orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));

        isNotNull(existingProduct::setName, productDto.product_name());
        isNotNull(existingProduct::setDescription, productDto.product_description());
        isNotNull(existingProduct::setPrice, productDto.price());
        isNotNull(existingProduct::setStock, productDto.stock());

        productRepository.update(existingProduct);
    }

    @Override
    @Transactional
    public void remove(long id) {
        productRepository.remove(new Product().setId(id));
    }

}
