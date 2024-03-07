package dev.ebyrdeu.e_commerce_jakarta.serivce.order;

import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import dev.ebyrdeu.e_commerce_jakarta.repository.order.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    public OrderServiceImpl() {
    }

    @Inject
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderDto> getAll() {
        return repository.getAll().stream().map(OrderDto::map).toList();
    }

    @Override
    public Order getOne(long id) {
        return repository.getOne(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));
    }

    @Override
    @Transactional
    public Order create(OrderDto dto) {
        return repository.create(OrderDto.map(dto));
    }

    @Override
    @Transactional
    public void update(long id, OrderDto dto) {
        var existingEntity = repository.getOne(id).orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));

        isNotNull(existingEntity::setStatus, dto.status());
        isNotNull(existingEntity::setOrderDate, dto.orderDate());

        repository.update(existingEntity);
    }

    @Override
    @Transactional
    public void remove(long id) {
        repository.remove(new Order().setId(id));
    }
}
