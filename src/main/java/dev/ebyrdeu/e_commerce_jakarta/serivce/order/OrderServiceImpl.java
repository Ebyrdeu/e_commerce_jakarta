package dev.ebyrdeu.e_commerce_jakarta.serivce.order;

import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.order.Orders;
import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import dev.ebyrdeu.e_commerce_jakarta.repository.order.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

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
    public Orders getAll() {
        return new Orders(
                repository.getAll().stream().map(OrderDto::map).toList()
        );
    }

    @Override
    public Order getOne(long id) {
        var order = repository.getOne(new Order().setId(id));

        if (order == null) {
            throw new NotFoundException("Product with id not found");
        }

        return order;
    }

    @Override
    public Order create(OrderDto dto) {

//        TODO: change to DTO
        return repository.create(OrderDto.map(dto));
    }

    @Override
    public Order update(long id, OrderDto dto) {
        return repository.update(new Order()
                .setId(id)
                .setOrderDate(dto.orderDate())
                .setStatus(dto.status())
        );
    }

    @Override
    public void remove(long id) {
        repository.remove(new Order().setId(id));
    }
}
