package dev.ebyrdeu.e_commerce_jakarta.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product", schema = "e_commerce_jakarta")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_to_product",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    private Set<Order> orders = new HashSet<>();

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;


    public Set<Order> orders() {
        return orders;
    }

    public Long id() {
        return id;
    }

    public Category category() {
        return category;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public BigDecimal price() {
        return price;
    }

    public Integer stock() {
        return stock;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Date updatedAt() {
        return updatedAt;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public Product setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
