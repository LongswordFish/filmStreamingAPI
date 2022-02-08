package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String username);

    User findUserById(Long id);
}
