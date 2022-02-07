package ca.wonderfish.filmstreamingapi.repositories;

import ca.wonderfish.filmstreamingapi.domains.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {

    @Override
    Iterable<Film> findAll();

    Film findFilmById(Long id);
}
