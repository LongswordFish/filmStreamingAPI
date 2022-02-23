package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    Iterable<Order> findAll();

    List<Order> findOrdersByCustomerEmail(String customerEmail);

    Order findOrderById(Long orderId);
}
