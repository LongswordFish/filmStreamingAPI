package ca.wonderfish.filmstreamingapi.services;

import ca.wonderfish.filmstreamingapi.domains.Film;
import ca.wonderfish.filmstreamingapi.exceptions.FilmIdException;
import ca.wonderfish.filmstreamingapi.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Iterable<Film> findAllFilms(){
        return  filmRepository.findAll();
    }

    public Film saveOrUpdateFilm(Film film){
        try{
            return filmRepository.save(film);
        }catch(Exception e){
            throw new FilmIdException("Film id - "+film.getId()+" save failed");
        }
    }

}
