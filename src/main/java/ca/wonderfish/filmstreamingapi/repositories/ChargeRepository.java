package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends CrudRepository<Charge,Long> {
}
