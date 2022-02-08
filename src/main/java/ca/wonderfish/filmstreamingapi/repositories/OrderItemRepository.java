package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
