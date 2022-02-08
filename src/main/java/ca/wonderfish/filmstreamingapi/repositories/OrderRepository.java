package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    Iterable<Order> findAll();

    Order findOrderByCustomerEmail(String customerEmail);

    Order findOrderById(Long orderId);
}
